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
	static AtomicInteger Hamming_ = new AtomicInteger(), cuatro_por_generacion_ = new AtomicInteger();
	static CyclicBarrier barrera = new CyclicBarrier(4);
	AtomicInteger[] CelulasEstado_,Celula7Estado_;

	Celular_automata(int inicio,int fin,Object cerrojo,int[] primera_generacion,
		int[] iesima_generacion,int Ncells,int r, int k,boolean con,int[] reg,AtomicInteger[] CellsEstado,
		AtomicInteger[] Celula7Estado)
	{

		anterior_ = primera_generacion;
		siguiente_ = iesima_generacion;
		Ncelulas_ = Ncells;
		vecindad_ = r;
		Nestados_ = k;
		condicionBarrera = con;

		Celula7Estado_ = Celula7Estado;
		CelulasEstado_ = CellsEstado; 
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

			//probabilidades entropia espacial
			try{
			switch(siguiente_[i]){
				case 0:
				CelulasEstado_[0].incrementAndGet();
				if(i==7)
					Celula7Estado_[0].incrementAndGet();
				break;
				case 1:
				CelulasEstado_[1].incrementAndGet();
				if(i==7)
					Celula7Estado_[1].incrementAndGet();
				break;
				case 2:
				CelulasEstado_[2].incrementAndGet();
				if(i==7)
					Celula7Estado_[2].incrementAndGet();
				break;
				case 3:
				CelulasEstado_[3].incrementAndGet();
				if(i==7)
					Celula7Estado_[3].incrementAndGet();
				break;
				case 4:
				CelulasEstado_[4].incrementAndGet();
				if(i==7)
					Celula7Estado_[4].incrementAndGet();
				break;
				case 5:
				CelulasEstado_[5].incrementAndGet();
				if(i==7)
					Celula7Estado_[5].incrementAndGet();
				break;
				default:
				break;
			}
			}catch(IndexOutOfBoundsException e){System.out.println("Se han excedido los limites de Celular_automata::CelulasEstado_");}		
		

			//distancia de hamming interconfiguracion
			if(siguiente_[i] != anterior_[i])
				Hamming_.incrementAndGet();
		}
		try{
		barrera.await();
		}catch(BrokenBarrierException e){ e.getMessage(); }
		 catch(InterruptedException e){ e.getMessage(); }
		
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
	int vecindad,Nestados,Ncelulas,tamRegla,Ngeneraciones;
	public int[] primeraGen,iesimaGen,regla;
	private int nucleos = Runtime.getRuntime().availableProcessors();
	AtomicInteger[] CelulasEstado,Celula7Estado; 
	
	ca1DSim(int cells, int[] primGen, int r, int k, boolean conFront,int idRegla)
	{
		Ngeneraciones = 0;
		Ncelulas = cells;
		vecindad = r;
		Nestados = k;
		condicionFront = conFront;
		tamRegla = (int)Math.pow( (int)k,(int)(2*r + 1)*(k-1) );
		regla = new int[tamRegla];
		primeraGen = primGen;
		iesimaGen = new int[Ncelulas];
		creaRegla(idRegla);
		CelulasEstado = new AtomicInteger[Nestados];
		Celula7Estado = new AtomicInteger[Nestados];
		for(int i=0 ; i<Nestados ; ++i)
		{
			CelulasEstado[i]=new AtomicInteger(0);
			Celula7Estado[i]=new AtomicInteger(0);
		}
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
		++Ngeneraciones;
		int ventana = Ncelulas/nucleos;
		int inicio = 0, fin = ventana;
		Object cerrojo =  new Object();
		Celular_automata.Hamming_ = new AtomicInteger(0);//la distancia de hamming es entre dos configuraciones no entre todas las generaciones generadas

		ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(nucleos,nucleos,0L,
        TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

		for(int i = 0; i<=nucleos-1 ; ++i)
        {    
        	ejecutor.execute(new Celular_automata(inicio,fin,cerrojo,primeraGen,iesimaGen,
            Ncelulas,vecindad,Nestados,condicionFront,regla,CelulasEstado,Celula7Estado));

            inicio += ventana;
            fin += ventana;
        }
		try{
          ejecutor.shutdown();
          ejecutor.awaitTermination(1L,TimeUnit.DAYS);
        }catch(InterruptedException e){}

	}

	public double probabilidadCelulaSeaEstado(int i)
	{
		try{
			//System.out.print("CelulasTotales: "+Ncelulas+"CelulasEstado"+i+": "+CelulasEstado[i]);
			return (double)CelulasEstado[i].get()/(double)(Ncelulas*Ngeneraciones);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Se han excedido los límites del array ca1DSim::CelulasEstado");
			return 0.0;
		}
	}

	public double entropia_espacial()
	{
		double entropia=0;
		for(int i=0 ; i<Nestados ; ++i)
			entropia += probabilidadCelulaSeaEstado(i)*(Math.log(probabilidadCelulaSeaEstado(i))/Math.log(Nestados));
		
		return (-1)*entropia;
	}

	public int distanciaHamming()
	{
		return Celular_automata.Hamming_.intValue();
	}

	public double probabilidad_cell7(int estado_i)
	{
		try{
			return (double)Celula7Estado[estado_i].get()/(double)(Ngeneraciones);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Se han excedido los límites del array ca1DSim::CelulasEstado");
			return 0.0;
		}
	}

	public double entropia_temporalCell7()
	{
		double entropia = 0;
		for(int i=0 ; i<Nestados ; ++i)
			entropia += probabilidad_cell7(i)*(Math.log(probabilidad_cell7(i))/Math.log(Nestados));
		
		return (-1)*entropia;
	}
}