package client;




import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Window extends JFrame{

	public String receiverName;
    private Socket socket;
    private Client client;
	private String clientName;
	JPanel messages;
	JPanel messages2;
	
	

	public Window(Client client,String receiverName) {
		this.client=client;
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension (1200,800));
		this.receiverName=receiverName;
		clientName=client.getClientName();
		
		setTitle(receiverName);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		
		
		

        // Add a window listener
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                // Make the JFrame invisible
                Window.this.setVisible(false);

            }
        });
		
		
        
        
        
        

		
		
		
		JTextArea textInput;
		JScrollPane scroll;
		 JScrollPane scrollMessages;
		JButton send;
		  JPanel mainPanel = new JPanel();
		
		
		
		
		setBackground(Color.GRAY);

		
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu  = new JMenu("File");
		JMenuItem clearItem = new JMenuItem("Disconnect");
		
		
		clearItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				client.removeWindow(Window.this);
				setVisible(false);

				
				
			}
		});
		
		
		fileMenu.add(clearItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		
		
		mainPanel.setLayout(new GridLayout(1,2));
		 mainPanel.setBackground(Color.decode("#E8DFF5"));
		 mainPanel.setBorder(new EmptyBorder(10,30,10,30));
		 

        textInput = new JTextArea();
        textInput.setColumns(30);
        textInput.setRows(2);
        textInput.setLineWrap(true);
        textInput.setFont(new Font("Arial",Font.PLAIN,20));
        JScrollPane textScroll = new JScrollPane(textInput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        
        send = new JButton("Send");
        send.setFont(new Font("Arial",Font.BOLD,20));
		send.setBackground(Color.decode("#fce1e4"));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(100,100));
        
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.decode("#E8DFF5"));
        
        bottomPanel.add(textScroll); // Add textScroll
        bottomPanel.add(send);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
       Color allMessagesColor= Color.decode("#faf3f0");
        
        messages = new JPanel();
        messages.setLayout(new BoxLayout(messages,BoxLayout.Y_AXIS));
        messages.setBackground(allMessagesColor);
      

        
        
        
        messages2 = new JPanel();
        messages2.setLayout(new BoxLayout(messages2,BoxLayout.Y_AXIS));
        messages2.setBackground(allMessagesColor);

        
         scrollMessages = new JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         
         

mainPanel.addComponentListener(new ComponentAdapter() {
    @Override
    public void componentResized(ComponentEvent e) {
        scrollMessages.getVerticalScrollBar().setValue(scrollMessages.getVerticalScrollBar().getMaximum());
    }
});

         
         
        
        mainPanel.add(messages);
        mainPanel.add(messages2);
        
        
        
        
        
        add(scrollMessages);

        getContentPane().setBackground(Color.PINK);

		
		

		setVisible(false);

		
		
		send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(!textInput.getText().trim().isEmpty()) {

            		
            		
            		JLabel message1 = new JLabel("<html><p style='width:300px'>" + textInput.getText().replace("\n", "<br>") + "</p></html>");
            		
            		 JLabel message2 = new JLabel("<html><p style='width:300px'>" + textInput.getText().replace("\n", "<br>") + "</p></html>");
            		

            		
            		 
            		 

            		 JLabel space = new JLabel(" ");
            		 space.setPreferredSize(new Dimension(10,10));
            		 
            		 JLabel space2 = new JLabel(" ");
            		 space.setPreferredSize(new Dimension(10,10));
            		 
            		 
            		 
            		 
            		 
            		 message1.setOpaque(true);
            		 message1.setBackground(Color.decode("#ddedea"));
            		 message1.setForeground(Color.black);
            		 message1.setFont(new Font("Arial",Font.BOLD,30));
            		 
            		 message2.setOpaque(false);
            		 message2.setForeground(Color.decode("#faf3f0"));
            		 message2.setFont(new Font("Arial",Font.BOLD,30));
            		 
            		 
            		
            		 

            		 
            		 JLabel me = new JLabel("ME:");
            		 
            		 messages.add(me);
            		 messages.add(message1);
            		 messages.add(space);
            		 messages.revalidate();
            		 messages.repaint();
            		 
            		 
            		 
            		 messages2.add(new JLabel(" "));
            		 messages2.add(message2);
            		 messages2.add(space2);
            		 messages2.revalidate();
            		 messages2.repaint();
            		 
            		 
            		 
            	
            	String newMessage= textInput.getText().replace("\n", "@@@@");
            	
            	client.sendPrivateMessage(receiverName, newMessage );
            	
            	}
            	
            	textInput.setText("");            	
                
            }//if
        });
		
		
		



		
		
	}//cons
	
	
	
	

}
