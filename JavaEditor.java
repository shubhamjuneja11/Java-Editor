import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class JavaEditor implements ActionListener{
	JFrame jf;
	JLabel jl,editor,errorreport;
	JTextArea ja;
	JScrollPane jsp,jsp2;
	JTextArea jta,jta2;
	JTextField jtf;
	JButton go,compile,run,newclass,save;
	Runtime r;
	String result="",result1="";
	public JavaEditor(){
		jf=new JFrame("Java Editor");
		r=Runtime.getRuntime();	
		jf.setLayout(null);
		jf.setSize(900,900);
		jl=new JLabel("Enter Java class Name:");	
		editor=new JLabel("Editing Area:");
		errorreport=new JLabel("Console:");
		ja=new JTextArea(50,50);
		go=new JButton("Go");
		compile=new JButton("Compile");
		run=new JButton("Run");
		save=new JButton("Save");
		newclass=new JButton("New");
		run=new JButton("Run");
		jtf=new JTextField();
		//jtf2=new JTextField();
		jta2=new JTextArea();
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jsp2=new JScrollPane(jta2);
		//ja.setFont(new Font())

//Textfields
		jl.setBounds(50,20,200,100);	//label
		jsp.setBounds(300,20,100,100);	//scrollpane
		jtf.setBounds(220,55,150,30);					//class name
		editor.setBounds(220,70,300,100);
		jsp.setBounds(220,150,500,400);		//editing area
		jsp2.setBounds(220,580,500,200);	//error area
		errorreport.setBounds(50,580,80,80);
		save.setBounds(50,350,100,30);
		//Buttons
		go.setBounds(380,55,90,30);
		newclass.setBounds(50,150,100,30);
		compile.setBounds(50,210,100,30);
		run.setBounds(50,270,100,30);

		
		jf.add(ja);
jf.add(save);

		jf.add(jl);
		jf.add(jtf);
		jf.add(jsp);
		jf.add(editor);
		jf.add(go);
		jf.add(newclass);
		jf.add(compile);
		jf.add(run);
		jf.add(jsp2);
		jf.add(errorreport);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//adding actionlisteners

		compile.addActionListener(this);
		run.addActionListener(this);
		go.addActionListener(this);
		save.addActionListener(this);
	}
	public void actionPerformed(ActionEvent  e){
File f;
FileWriter fw;
PrintWriter pw;
String name,str;name="";
				if(e.getSource()==go)
				{
					jta.setText("public class "+jtf.getText().toString()+"{\n public static void main(String s[]){\n} \n}");	
				}
				else if(e.getSource()==save){
					if(!jta.getText().equals("")){
						try{
							name=jtf.getText().trim()+".java";
							 f=new File(name);
							fw=new FileWriter(f);
							//f.setExecutable(true);
							str=jta.getText();
							 pw=new PrintWriter(fw);
							pw.println(str);
							pw.flush();
						}
						catch(Exception r){r.printStackTrace();}
					}
					

				}
				else if(e.getSource()==compile){
					
					if(!jta.getText().equals("")){
						try{
							/*name=jtf.getText().trim()+".java";
							File f=new File(name);
							FileWriter fw=new FileWriter(f);
							f.setExecutable(true);
							str=jta.getText();
							PrintWriter pw=new PrintWriter(fw);
							pw.println(str);
							pw.flush();*/
							Process p=r.exec("/home/junejaspc/F:/Codes/"+name);
							BufferedReader err=new BufferedReader(new InputStreamReader(p.getErrorStream()));
							BufferedReader output=new BufferedReader(new InputStreamReader(p.getInputStream()));
							while(true){
								String temp=err.readLine();
								if(temp!=null){
									result+=temp+"\n";
								}
								else break;

								while(true){
									temp=output.readLine();
									if(temp!=null){
										result+=temp+"\n";
									}
									else break;
								}
							}
							output.close();err.close();
							jta2.setText(result);
							//p.close();
						}
						catch(Exception e1){
							e1.printStackTrace();
						}
					}
				}
	}
	public static void main(String s[]){
		new JavaEditor();
	}
}