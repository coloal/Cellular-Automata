import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;        
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.LayoutStyle.ComponentPlacement.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.concurrent.*;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Simulatore implements ActionListener,ItemListener{
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    static JFrame main_frame,frame_options,frame_stats;
    JFrame[] frames_menu1,frames_submenu_menu1,frames_menu2;
    JFrame menu;

    GraphicPanelRandom main_pane;
    Graphics Gmain_pane;

    JTextField field1,field2,field3,field4;//fields de pane Stats
    JComboBox<String> genList;
    JTextField textField1,textField2,textField3,textField4,textField5,textField6,Field1,Field2,Field3,Field4;
    
   // double x_;
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/MenuLookDemoProject/src/components/MenuLookDemo.java
   /*Simulatore(){
   	x_=0;
   }*/

  	public void actionPerformed(ActionEvent e){
  		if("comboBoxChanged".equals(e.getActionCommand()))
  		{
  			//JComboBox<String> cb = (JComboBox<String>)e.getSource<JComboBox<String>>();
  			String generador = (String)genList.getSelectedItem();//Esto si hay más de una comboBox no funciona pero he tenido que usarlo para evitar un warning unchecked cast de la línea de arriba en la que se convierte una String en ComboBox y no tiene solución
  			
  			if("26-1a".equals(generador))
  			{
  				main_pane.setGenerador(1);      
  			}
  			else if("26-1b".equals(generador))
  			{
  				main_pane.setGenerador(2);
  			}
  			else if("26-2".equals(generador))
  			{
  				main_pane.setGenerador(3);
  			}
  			else if("26-3".equals(generador))
  			{
  				main_pane.setGenerador(4);
  			}
  			else if("26-42".equals(generador))
  			{
  				main_pane.setGenerador(5);	
  			}
  			else if("Fishman & Moore1".equals(generador))
  			{
  				main_pane.setGenerador(6);
  			}
  			else if("Fishman & Moore2".equals(generador))
  			{
  				main_pane.setGenerador(7);
  			}
  			else if("Randu".equals(generador))
  			{
  				main_pane.setGenerador(8);
  			}	
  		}
  		else if("checkBoxChanged".equals(e.getActionCommand()))
  		{
  			menu = new JFrame(e.getActionCommand());
  			menu.setPreferredSize(new Dimension(400,40));
			  menu.pack();
  			menu.setVisible(true);

  			//field2.setText(e.getActionCommand());
  			//if("".equals(str))
  		}
  		else
  		{
        if("DIBUJAR".equals(e.getActionCommand()))
        {
          main_pane.dibujar();
          //main_pane.paintComponent(Gmain_pane);
          field1.setText(" "+main_pane.getSemilla());
          
        }
  		else if("FIN".equals(e.getActionCommand()))
        {
        	//main_pane.dibujar();
        }
        else if("txtField1".equals(e.getActionCommand())){
  			main_pane.setSemilla(textField1.getText());
  		}
  		else if("txtField2".equals(e.getActionCommand())){
  			main_pane.setSemillaW(textField2.getText());
  		}
  		else if("txtField3".equals(e.getActionCommand()))
  		{
  			main_pane.setSemillaX(textField3.getText());
  		}
  		else if("txtField4".equals(e.getActionCommand()))
  		{
  			main_pane.setSemillaY(textField4.getText());
  		}else if("txtField5".equals(e.getActionCommand()))
     	{
       	    main_pane.setRegla0_256(Integer.parseInt(textField5.getText()));
      	}
  		else if("Field1".equals(e.getActionCommand()))
  		{
  			main_pane.setNcelulas(Integer.parseInt(Field1.getText()));
  		}
  		else if("Field2".equals(e.getActionCommand()))
  		{
        	main_pane.setm(Field2.getText());
       	    main_pane.setEstado(Integer.parseInt(Field2.getText()));
  		}
      	else if("Field3".equals(e.getActionCommand()))
     	 {
        	main_pane.setVecindad(Integer.parseInt(Field3.getText()));
      	}else if("Field4".equals(e.getActionCommand()))
      	{
       	    main_pane.setCondicionFrontera(Integer.parseInt(Field4.getText()));
     	 }
  		else
  		{
  			menu = new JFrame(e.getActionCommand()+" "+textField1.getText());
        	menu.setPreferredSize(new Dimension(400,40));
        	menu.pack();
        	menu.setVisible(true);
  			
  		}
  		}			
  		
  	}


  	public void itemStateChanged(ItemEvent e){
  		if(e.getStateChange() == ItemEvent.SELECTED)
  		{
  			menu = new JFrame("hey");
  		menu.setPreferredSize(new Dimension(400,40));
  		menu.pack();
  		menu.setVisible(true);
  		}
  		else{
  			menu.setVisible(false);
  		}
  	}

    public JMenuBar createMenuBar_MainFrame(){
    	//Crea la barra de menú
        JMenuBar BarraMenu = new JMenuBar();
        BarraMenu.setOpaque(true);
        BarraMenu.setBackground(new Color(51,134,202));
        BarraMenu.setPreferredSize(new Dimension(480,20));

        //Crea el menú1 en sí
        JMenu menu = new JMenu("menu1");
        JMenu menu2 = new JMenu("menu2");
        menu.setMnemonic(KeyEvent.VK_A);//'alt + A' y se despliega el menu1
        
        //Creamos un item y el Accelerator crea un shortcut para pulsar alt+B y queda especificada la combinación de letras en la GUI para activar el ItemAccelerator
        //el accelerator solo se puede usar con items con JMenuItems no se puede, habría que usar setMnemonic
        JMenuItem item_altB = new JMenuItem("ItemAccelerator");//,KeyEvent.VK_B);
        item_altB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.ALT_MASK));
        item_altB.addActionListener(this);//Gestiona eventos
        item_altB.setActionCommand("ItemAccelerator");
        menu.add(item_altB);

        menu.addSeparator();//----------------------------------------

        JMenuItem menuItem; 
        //menu item_i
        for( int i=1 ; i<=3 ; ++i)
        {
        menuItem = new JMenuItem("item"+i);
        menuItem.addActionListener(this);
        menuItem.setActionCommand("item"+i);
        menu.add(menuItem);
        }

       

        String[] color_pelo = {"Rubio","Moreno","Pelirrojo","Castano"};
        ButtonGroup group = new ButtonGroup();
        JRadioButton menu_item_pelo = new JRadioButton(color_pelo[0]);
        for( int i = 0 ; i <= color_pelo.length-1 ; ++i )
        {
        	menu_item_pelo = new JRadioButton(color_pelo[i]);
        	menu_item_pelo.addActionListener(this);
        	menu_item_pelo.setActionCommand(color_pelo[i]);
        	group.add(menu_item_pelo);
        	menu2.add(menu_item_pelo);
        }  
        menu_item_pelo.setSelected(true);
       

        menu.addSeparator();//-----------------------------------------------
        
        //Creamos submenu dentro de menu
        JMenu submenu = new JMenu("Submenu");
        
        String[] items_submenu = {"item1_submenu","item2_submenu","item3_submenu"};
        JMenuItem item_submenu;
        for(int i = 0 ; i<=items_submenu.length-1 ; ++i)
        {
        	item_submenu = new JMenuItem(items_submenu[i]);
        	item_submenu.addActionListener(this);
        	item_submenu.setActionCommand(items_submenu[i]);
        	submenu.add(item_submenu);
        }
        menu.add(submenu);
        
        //añade menu a barra de menu
        BarraMenu.add(menu);
        BarraMenu.add(menu2);
        return BarraMenu;
    }

    public Container createContentPane_MainFrame(){
    	main_pane = new GraphicPanelRandom();
    	
        return main_pane;
    }

    //JTextField, JRadiobottonY,JSileder,JSpinners,JCheckBox
    public Container createContentPane_Options1_child1()
    {
    	JPanel Panel1_options = new JPanel(); 	
  		
  		JLabel label_textField1 = new JLabel("SEMILLA"),label_textField2 = new JLabel("SEMILLA w"),label_textField3 = new JLabel("SEMILLA x"),label_textField4 = new JLabel("SEMILLA Y");
 		JLabel label_textField5 = new JLabel("fTransicion"),label_textField6 = new JLabel("Nada");
 		textField1 = new JTextField("1"); textField2 = new JTextField("3");
 	    textField3 = new JTextField("1"); textField4 = new JTextField("5");
  		textField5 = new JTextField("100"); textField6 = new JTextField("0");
  		textField1.addActionListener(this);textField2.addActionListener(this);textField3.addActionListener(this);
  		textField4.addActionListener(this);textField5.addActionListener(this);textField6.addActionListener(this);
  		textField1.setActionCommand("txtField1"); textField2.setActionCommand("txtField2");
  		textField3.setActionCommand("txtField3"); textField4.setActionCommand("txtField4");
  		textField5.setActionCommand("txtField5"); textField6.setActionCommand("txtField6");

  		//JRadioButton radio_botton1 = new JRadioButton("OtraOpcion1"),radio_botton2 = new JRadioButton("OtraOpcion2");
  		//JRadioButton radio_botton3 = new JRadioButton("Opcion3"),radio_botton4 = new JRadioButton("Opcion4");
  		
  		JCheckBox check_box1 = new JCheckBox("Check1"),check_box2 = new JCheckBox("Check2");
  		JCheckBox check_box3 = new JCheckBox("Check3"),check_box4 = new JCheckBox("Check4");
  		check_box1.addActionListener(this);check_box2.addActionListener(this);

  		GroupLayout layout = new GroupLayout(Panel1_options);
  		layout.setAutoCreateGaps(true);
  		layout.setAutoCreateContainerGaps(true);
  		Panel1_options.setLayout(layout);

  		layout.setHorizontalGroup(layout.createSequentialGroup()
  			.addGroup(layout.createParallelGroup(TRAILING)
  				.addComponent(check_box1)
  				.addComponent(check_box2)
  			)
  			.addGroup(layout.createParallelGroup(TRAILING)
  				.addComponent(label_textField1)
  				.addComponent(label_textField2)
  			)
  			.addGroup(layout.createParallelGroup(LEADING)
  				.addComponent(textField1)
  				.addComponent(textField2)
  			)
 			.addGroup(layout.createParallelGroup(LEADING)
 				.addComponent(label_textField3)
 				.addComponent(label_textField4)
 			)
 			.addGroup(layout.createParallelGroup(LEADING)
 				.addComponent(textField3)
 				.addComponent(textField4)
 			)
 			.addGroup(layout.createParallelGroup(LEADING)
 				.addComponent(label_textField5)
 				.addComponent(label_textField6)
 			)
 			.addGroup(layout.createParallelGroup(LEADING)
 				.addComponent(textField5)
 				.addComponent(textField6)
 			)
  		);

  		

  		layout.setVerticalGroup(layout.createSequentialGroup()
  			.addGroup(layout.createParallelGroup(BASELINE)
  				.addComponent(check_box1)
  				.addComponent(label_textField1)
  				.addComponent(textField1)
  				.addComponent(label_textField3)
  				.addComponent(textField3)
  				.addComponent(label_textField5)
  				.addComponent(textField5)
  			)
  			.addGroup(layout.createParallelGroup(BASELINE)
  				.addComponent(check_box2)
  				.addComponent(label_textField2)
  				.addComponent(textField2)
  				.addComponent(label_textField4)
  				.addComponent(textField4)
  				.addComponent(label_textField6)
  				.addComponent(textField6)
  			)
  		);

  		Panel1_options.setBackground(new Color(176,201,247));
    	Panel1_options.setBorder(BorderFactory.createLineBorder(new Color(51,134,202)));


    	return Panel1_options;
    }

    public Container createContentPane_Options1_child2(){
    	JPanel pane = new JPanel();
    	//pane.setTitle("Titulo");
    	//GroupLayout layout = new GroupLayout(pane);
  	

  		JSlider slider1 = new JSlider(JSlider.VERTICAL,0,300,150),slider2 = new JSlider(JSlider.VERTICAL,0,300,150);//tipo,minimo,maximo,empieza
  		slider1.createStandardLabels(30);
  		slider1.setMajorTickSpacing(30);
        slider1.setMinorTickSpacing(15);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.addChangeListener(new SliderListener());

  		pane.add(slider1);
  		//pane.setPreferredSize(new Dimension(120,240));
  		Border borde = BorderFactory.createLineBorder(new Color(51,134,202));
  		TitledBorder borde_titulo = BorderFactory.createTitledBorder(borde,"Dato Silder");
 		
    	pane.setBorder(borde_titulo);
    	pane.setBackground(new Color(176,201,247));
    	pane.setPreferredSize(new Dimension(100,240));
    	return pane;
    }

    public Container createContentPane_Options1_child3(){
    	JPanel pane = new JPanel();
    	
    	
      Field1 = new JTextField("640"); Field2 = new JTextField("3");
      Field3 = new JTextField("1"); Field4 = new JTextField("5");
      Field1.addActionListener(this);Field2.addActionListener(this);Field3.addActionListener(this);
      Field4.addActionListener(this);
      Field1.setActionCommand("Field1"); Field2.setActionCommand("Field2");
      Field3.setActionCommand("Field3"); Field4.setActionCommand("Field4");
  		JLabel label_spinner1 = new JLabel("Ncelulas"),label_spinner2 = new JLabel("Nestados"),label_spinner3 = new JLabel("Nvecinos");
    	JLabel label_spinner4 = new JLabel("condicionFrontera");

    	GroupLayout layout = new GroupLayout(pane);
  		layout.setAutoCreateGaps(true);
  		layout.setAutoCreateContainerGaps(true);
  		pane.setLayout(layout);

  		layout.setHorizontalGroup(layout.createSequentialGroup()
  			.addGroup(layout.createParallelGroup(TRAILING)
  				.addComponent(label_spinner1)
  				.addComponent(Field1)
  			)
  			.addGroup(layout.createParallelGroup(TRAILING)
  				.addComponent(label_spinner2)
  				.addComponent(Field2)
  			)
  			.addGroup(layout.createParallelGroup(LEADING)
  				.addComponent(label_spinner3)
  				.addComponent(Field3)
  			)
 			.addGroup(layout.createParallelGroup(LEADING)
 				.addComponent(label_spinner4)
 				.addComponent(Field4)
 			)
  		);

  		

  		layout.setVerticalGroup(layout.createSequentialGroup()
  			.addGroup(layout.createParallelGroup(BASELINE)
  				.addComponent(label_spinner1)
  				.addComponent(label_spinner2)
  				.addComponent(label_spinner3)
  				.addComponent(label_spinner4)
  			)
  			.addGroup(layout.createParallelGroup(BASELINE)
  				.addComponent(Field1)
  				.addComponent(Field2)
  				.addComponent(Field3)
  				.addComponent(Field4)
  			)
  		);


    	Border borde = BorderFactory.createLineBorder(new Color(51,134,202));
  		TitledBorder borde_titulo = BorderFactory.createTitledBorder(borde,"Datos Spinner");
 		
    	pane.setBorder(borde_titulo);
    	pane.setBackground(new Color(176,201,247));
    	//pane.setPreferredSize(new Dimension(100,240));

    	return pane;
    }

    public Container createContentPane_Options1_child4()
    {
    	JPanel pane = new JPanel();

    	JButton inicio = new JButton("DIBUJAR"),fin = new JButton("FIN");
  		inicio.addActionListener(this);
  		fin.addActionListener(this);

  		String[] generadores = {"26-1a","26-1b","26-2","26-3","26-42","Fishman & Moore1","Fishman & Moore2","Randu"};
  		genList = new JComboBox<String>(generadores);
  		genList.addActionListener(this);

  		GroupLayout layout = new GroupLayout(pane);
  		layout.setAutoCreateGaps(true);
  		layout.setAutoCreateContainerGaps(true);
  		pane.setLayout(layout);

  		layout.setHorizontalGroup(layout.createSequentialGroup()
  			.addGroup(layout.createParallelGroup(LEADING)
  				.addComponent(genList)
  				.addComponent(inicio)
  			)
  			.addGroup(layout.createParallelGroup(LEADING)
  				.addComponent(fin)
  			)
  		);  		

  		layout.setVerticalGroup(layout.createSequentialGroup()
  			.addGroup(layout.createParallelGroup(BASELINE)
  				.addComponent(genList)
  			)
  			.addGroup(layout.createParallelGroup(BASELINE)
  				.addComponent(inicio)
  				.addComponent(fin)
  			)
  		);

  		Border borde = BorderFactory.createLineBorder(new Color(51,134,202));
  		TitledBorder borde_titulo = BorderFactory.createTitledBorder(borde,"Datitos y botones");
 		
    	pane.setBorder(borde_titulo);
    	pane.setBackground(new Color(176,201,247));
    	//pane.setPreferredSize(new Dimension(100,240));

    	return pane;
    }

    public Container createContentPane_Options1Father(Container panel1,Container panel2,Container panel3,Container panel4)
    {
    	JPanel panelFather = new JPanel(new BorderLayout());
    	//GridLayout layout = new GridLayout(2,0);
    	//panelFather.setLayout();

    	panelFather.add(panel1,BorderLayout.PAGE_START);
    	panelFather.add(panel2,BorderLayout.LINE_END);
    	panelFather.add(panel3,BorderLayout.PAGE_END);
    	panelFather.add(panel4,BorderLayout.CENTER);

    	panelFather.setBackground(new Color(51,134,202));
    	return panelFather;
    }

    public Container createContentPane_Options2()
    {
    	JPanel Panel2_options = new JPanel();
    	
    	return Panel2_options;
    }


    public Container createTabbedContentPane_OptionsFrame(){
    	
    	JTabbedPane tabbedPane = new JTabbedPane();
    	tabbedPane.addTab("Parametros", createContentPane_Options1Father(createContentPane_Options1_child1(),
    		createContentPane_Options1_child2(),createContentPane_Options1_child3(),createContentPane_Options1_child4()));
    	tabbedPane.addTab("Opciones",createContentPane_Options2());
       	tabbedPane.setBackground(new Color(51,134,202));

        return tabbedPane;
    }

    public Container createContentPane_StatsChild1()
    {
    	JPanel Pane = new JPanel();
    	Pane.setPreferredSize(new Dimension(480,160));
    	
    	field1 = new JTextField("STATS1");field2 = new JTextField("STATS2");
    	//field3 = new JTextField("TXTFIELD3");field4 = new JTextField("TXTFIELD4");
    	field1.setEditable(false);field2.setEditable(false);//field3.setEditable(false);field4.setEditable(false);
    	Pane.add(field1);Pane.add(field2);//Pane.add(field3);Pane.add(field4);
    	return Pane;
    }

    public Container createContentPane_StatsFather(Container panel1/*,Container panel2,Container panel3*/)
    {
    	JPanel panelFather = new JPanel();
    	panelFather.add(panel1);

    	panelFather.setBackground(new Color(51,134,202));
    	return panelFather;
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        main_frame = new JFrame("Simulatore");//Creamos un JFrame que es un Top-level Container(contenedor de alto nivel,viene a ser la raíz de una jerarquía de contención)
        										//otro Top-level Container puede ser un JDialog(en este caso la raíz de la jerarquía sería el jDialog)todo contenedor de alto  
        										//nivel contiene a su vez un content pane,una barra de menus opcional,un layered pane y un glass pane.A su vez la layered pane
        										//contiene la barra de menú y la content pane permitiendo la ordenación-Z de
        										//otros componentes.La glass pane se usa habitualmente para interceptar input
        										//events que ocurren por encima del contenedor de alto nivel.
        /*MAIN FRAME*/
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setPreferredSize(new Dimension(640,480));
        //colocar main_frame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dim.getWidth()-main_frame.getWidth())/4);
        int y = (int) ((dim.getHeight()-main_frame.getHeight())/4);
        main_frame.setLocation(x,y);

       	Simulatore sim = new Simulatore();

       	main_frame.setContentPane(sim.createContentPane_MainFrame());
        main_frame.setJMenuBar(sim.createMenuBar_MainFrame());
        
        //Display the window.
        main_frame.pack();
        main_frame.setVisible(true);
        sim.Gmain_pane = sim.main_pane.getGraphics();
        /*FRAME OPTIONS*/
        frame_options = new JFrame("SimulatoreOpciones");
        frame_options.setPreferredSize(new Dimension(500,480));
        frame_options.setLocation(x+main_frame.getWidth(),y);
    	frame_options.setContentPane(sim.createTabbedContentPane_OptionsFrame());

    	frame_options.pack();
        frame_options.setVisible(true);

        /*FRAME STATS*/
        frame_stats = new JFrame("SimulatoreStats");
        frame_stats.setPreferredSize(new Dimension(640,240));
        frame_stats.setLocation(x,main_frame.getHeight()+y);
        frame_stats.setContentPane(sim.createContentPane_StatsFather(sim.createContentPane_StatsChild1()));

        frame_stats.pack();
    	frame_stats.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

//PANEL PARA DIBUJAR RANDOM ***PRACTICA 2****
class GraphicPanelRandom extends JPanel{
	private static final long serialVersionUID = 1L;//REVISAR
	int iteraciones,generador;
	static BigInteger semilla_,m_;
	BigInteger[] semillas42_;
	private static int veces_ = 0;
	randomGenerator rand;

	int[] generacion1_,generacionI_;
	int Ncelulas,generaciones,Nestados,ventana,vecin_,idRule;
	boolean condicionFrontera;

	public GraphicPanelRandom(){
		super(new BorderLayout());
		
		iteraciones = 0;
		
		semilla_ = new BigInteger("1");
		semillas42_ = new BigInteger[4];
		for(int i = 0; i<3 ; ++i)
			semillas42_[i] = new BigInteger("1");

   		vecin_=1;
   		condicionFrontera = false;
   		Nestados = 2;m_ = new BigInteger("2");
    	Ncelulas = 640;generaciones = 50;
    	idRule = 90;

    	generacion1_ = new int[Ncelulas];
    	generacionI_ = new int[Ncelulas];
    	
    	rand = new randomGenerator();
	}

	public void setIteraciones(int a){ iteraciones = a;setGenerador(generador);}
	
	public void setNcelulas(int a){Ncelulas = a;setGenerador(generador);}
	public void setCondicionFrontera(int a){
		if(a >= 1)
			condicionFrontera = true;
		else
			condicionFrontera = false;
		setGenerador(generador);
	}
	public void setVecindad(int a){vecin_ = a;setGenerador(generador);}
	public void setm(String m){ m_ = new BigInteger(m); setGenerador(generador);}
	public void setEstado(int a){Nestados=a;setGenerador(generador);}

	public void setRegla0_256(int a){idRule = a;setGenerador(generador);}

	public void setSemilla(String a){ semilla_ = new BigInteger(a);setGenerador(generador);}
	public void setSemillaW(String a){ semillas42_[0] = new BigInteger(a);setGenerador(generador);}
	public void setSemillaX(String a){ semillas42_[1] = new BigInteger(a);setGenerador(generador);}
	public void setSemillaY(String a){ semillas42_[2] = new BigInteger(a);setGenerador(generador);}
	public BigInteger getSemilla(){ return semilla_;}
	public BigInteger getSemillaW(){ return semillas42_[0];}
	public BigInteger getSemillaX(){ return semillas42_[1];}
	public BigInteger getSemillaY(){ return semillas42_[2];}
  

	public void setGenerador(int a){
		generador = a;
		int semi = semilla_.intValue();
		BigInteger sem = new BigInteger("0");
		switch(a){
		    case 1:
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
		          	semi = rand.ejemplo26_1a(semi);
		          	generacion1_[i] = semi%Nestados;
		        }
		    break;
		   	case 2:      
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
		          	semi = rand.ejemplo26_1b(semi);
		          	generacion1_[i] = semi%Nestados;
		        }
		    break;
		    case 3:
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
		         	semi = rand.ejemplo26_2(semi);
		         	generacion1_[i] = semi%Nestados;
		        }
		    break;
		    case 4:
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
			        semilla_ = rand.UnEjemplo26_3(semilla_);
			        sem = semilla_.mod(m_);
			        generacion1_[i] = sem.intValue();    
		        }
		    break;
		    case 5:
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
		        	semillas42_[3] = rand.UnEjemplo26_42(semillas42_);
		         	sem = semillas42_[3].mod(m_);
		            generacion1_[i] = sem.intValue();
		        }
		    break;
		    case 6:
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
		          	semilla_ = rand.fishman_moore1(semilla_);
		          	sem = semilla_.mod(m_);
		            generacion1_[i] = sem.intValue();
		        }
		    break;
		    case 7:
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
		            semilla_ = rand.fishman_moore2(semilla_);
		          	sem = semilla_.mod(m_);
		            generacion1_[i] = sem.intValue();
		        }
		    break;
		    case 8:
		        for(int i = 0 ; i <= Ncelulas - 1 ; ++i)
		        {
		            semilla_ = rand.randu(semilla_);
		          	sem = semilla_.mod(m_);
		            generacion1_[i] = sem.intValue();
		        }
		    break;
		}
	}

  	void dibujar(){
  		repaint();
  	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(veces_ > 1)
	    {
       		ca1DSim automata = new ca1DSim(Ncelulas,generacion1_,vecin_,Nestados,condicionFrontera,idRule);
       		for(int y=0;y<480;++y)
       		{
       			automata.nextGen();
       			for(int x = 0; x<Ncelulas ; ++x)
	       		{
	       			//System.out.print(generacion1_[x]);
	       			switch(generacion1_[x]){
						case 1:	
						g.setColor(Color.black);
						g.fillRect(x,y,1,1);
						break;
						case 2:
						g.setColor(Color.green);
						g.fillRect(x,y,1,1);
						break;
						case 3:	
						g.setColor(Color.red);
						g.fillRect(x,y,1,1);
						break;
						case 4:	
						g.setColor(Color.orange);
						g.fillRect(x,y,1,1);
						break;
						case 5:
						g.setColor(Color.yellow);
						g.fillRect(x,y,1,1);
						break;
						default:
						break;
					}
				}
				//System.out.println();		
       		}
        }
    	++veces_;
  	}
}

class SliderListener implements ChangeListener{
	double valor;
	JFrame fr;

	public double valor(){return valor;}

	public void stateChanged(ChangeEvent e){
		JSlider source = (JSlider)e.getSource();
		if(!source.getValueIsAdjusting()){
			valor = (double)source.getValue();//valor guarda el valor del dato introducido en el slider
			fr = new JFrame(" "+valor);
 			fr.setPreferredSize(new Dimension(400,40));
  			fr.pack();
  			fr.setVisible(true);
		}
	}

}

class SpinnerListener implements ChangeListener{
	Object lastValue;
	JFrame fr;
	//public double valor(){return valor;}


	public void stateChanged(ChangeEvent e){
		JSpinner source = (JSpinner)e.getSource();
		
		if(lastValue != null && !source.getValue().equals(lastValue))
		{
			fr = new JFrame(" "+lastValue);
 			fr.setPreferredSize(new Dimension(400,40));
  			fr.pack();
  			fr.setVisible(true);
		}
		lastValue = source.getValue();
    

	}

}

