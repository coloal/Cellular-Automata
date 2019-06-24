import java.util.concurrent.*;
import java.util.Random;
import java.math.BigInteger;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Color;
import java.awt.Graphics;


class Celular_automata implements Runnable{
	int[] anterior_,siguiente_,regla_;
	char[] base_k;
	Object lock_;
	private int inicio_,fin_,p;
	public int Ncelulas_,vecindad_,Nestados_;
	public boolean condicionBarrera;
	static AtomicInteger cuatro_por_generacion_ = new AtomicInteger();
	static CyclicBarrier barrera = new CyclicBarrier(4);

	Celular_automata(int inicio,int fin,Object cerrojo,int[] primera_generacion,
		int[] iesima_generacion,int Ncells,int r, int k,boolean con,int[] reg){

		anterior_ = primera_generacion;
		siguiente_ = iesima_generacion;
		Ncelulas_ = Ncells;
		vecindad_ = r;
		Nestados_ = k;
		condicionBarrera = con;

		regla_ = reg;
		base_k = new char[2*r+1];

		inicio_ = inicio;
		fin_ = fin;
		lock_ = cerrojo;

	}

	public void run(){

		for(int i = inicio_ ; i<=fin_ - 1 ; ++i)
		{
			int p = 0;
			if((i-vecindad_)>=0 && (i+vecindad_)<Ncelulas_)
			{
				for(int y = i-vecindad_ ; y <= i+vecindad_ ; ++y)
					{
						base_k[p]=Character.forDigit(anterior_[y],Nestados_);
						++p;
					}
					siguiente_[i] = regla_[Integer.parseInt(String.valueOf(base_k),Nestados_)];		
			}
			else if(condicionBarrera)
			{
				if(i-vecindad_<0 && i+vecindad_<Ncelulas_)
				{
					for(int k = Ncelulas_-(vecindad_-i);k<Ncelulas_;++k)
					{
						base_k[p]=Character.forDigit(anterior_[k],Nestados_);
						++p;
					}
					for(int w = 0 ; w<=(i+vecindad_) ; ++w)
					{
						base_k[p]=Character.forDigit(anterior_[w],Nestados_);
						++p;
					}
					siguiente_[i] = regla_[Integer.parseInt(String.valueOf(base_k),Nestados_)];
				}
				else if(i-vecindad_ >= 0 && i+vecindad_ >= Ncelulas_)
				{
					for(int z = i-vecindad_ ; z < Ncelulas_ ; ++z)
					{
						base_k[p] = Character.forDigit(anterior_[z],Nestados_);
						++p;
					}
					for(int v = 0; v<(i+vecindad_)%Ncelulas_ ; ++v)
					{
						base_k[p] = Character.forDigit(anterior_[v],Nestados_);
						++p;
					}
					siguiente_[i] = regla_[Integer.parseInt(String.valueOf(base_k),Nestados_)];
				}
			}			
		}
		try{
		barrera.await();
		}catch(BrokenBarrierException e){ e.getMessage(); }
		 catch(InterruptedException e){ e.getMessage(); }
		
		//cuatro_por_generacion_.incrementAndGet();
		
		for(int j = inicio_ ; j <=fin_ - 1 ; ++j)
		{
			anterior_[j] = siguiente_[j];
		}

		try{
		barrera.await();
		}catch(BrokenBarrierException e){ e.getMessage(); }
		 catch(InterruptedException e){ e.getMessage(); }
}

	public static void main(String[] args) throws InterruptedException{
	}
}

class ca1DSim{
	boolean condicionFront;
	int vecindad,Nestados,Ncelulas,tamRegla;
	public int[] primeraGen,iesimaGen,regla;
	private int nucleos = Runtime.getRuntime().availableProcessors();

	ca1DSim(int cells, int[] primGen, int r, int k, boolean conFront,int idRegla)
	{
		Ncelulas = cells;
		vecindad = r;
		Nestados = k;
		condicionFront = conFront;
		tamRegla = (int)Math.pow( (int)k,(int)(2*r + 1)*(k-1) );
		regla = new int[tamRegla];
		primeraGen = primGen;
		iesimaGen = new int[Ncelulas];
		creaRegla(idRegla);
	}

	public void creaRegla(int idReg)
	{
		for(int j = 0; j<=tamRegla-1 ; ++j)
		{
			regla[j] = idReg%Nestados;
			idReg = (int)idReg/Nestados;
		}
	}

	public void nextGen()
	{
		int ventana = Ncelulas/nucleos;
		int inicio = 0, fin = ventana;
		Object cerrojo =  new Object();
		ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(nucleos,nucleos,0L,
        TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

		for(int i = 0; i<=nucleos-1 ; ++i)
        {    
        	ejecutor.execute(new Celular_automata(inicio,fin,cerrojo,primeraGen,iesimaGen,
            Ncelulas,vecindad,Nestados,condicionFront,regla));

            inicio += ventana;
            fin += ventana;
        }
		try{
          ejecutor.shutdown();
          ejecutor.awaitTermination(1L,TimeUnit.DAYS);
        }catch(InterruptedException e){}

	}
}