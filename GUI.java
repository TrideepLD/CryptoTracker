import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class GUI extends JFrame {
	
	static Arb arb = new Arb();

    private JPanel contentPane;
    static JTextPane price_left_top = new JTextPane();
    static String left_top_text = "Some text in text pane here\r\n\r\nLorem ipsum dolor sit amet,\nconsectetur adipiscing elit.";

    static JTextPane cryptoText = new JTextPane();
    static String crypto = "Lorem ipsum dolor sit amet, \r\nconsectetur adipiscing elit.\r\n Sed pellentesque tellus \r\ntellus, quis volutpat tortor\r\n imperdiet id. Vestibulum in\r\nquam nec lorem dictum \r\nlaoreet consequat sed dui. ";

    static JTextPane Lorem_1 = new JTextPane();
    static String arbLorem_1 = "Lorem Ipsum\r\n~~~~~~~~~~\r\n\r\nLorem ipsum dolor sit amet, consect\r\netur adipiscing elit.\r\nSed sed orci congue, dictum eros eget, gravida velit.\r\nQuisque pretium nibh at ligula cursus commodo.\r\nMaecenas bibendum enim sit amet diam hendrerit interdum.\r\nMauris sit amet nunc sed augue porttitor fringilla.\r\n";

    static JTextPane Lorem_2 = new JTextPane();
    static String arbLorem_2 = ("Lorem Ipsum\r\n~~~~~~~~~~~~~\r\n\r\n    Proin non lorem vitae magna tincidunt efficitur.\r\n    Donec ultricies diam in vulputate consequat.\r\n    Suspendisse at nisl maximus, tempor urna eu, fermentum turpis.\r\n Fusce viverra nisl at gravida lobortis.\r\nNulla at felis ornare, placerat nulla id, aliquet tortor.\r\n");

    static JTextPane Lorem_3 = new JTextPane();
    static String arbLorem_3 = ("Lorem Ipsum\r\n~~~~~~~~~~~~~\r\n\r\nEtiam pharetra justo quis maximus convallis.\r\nProin congue risus sed augue malesuada posuere.\r\nSed aliquam libero vel convallis sagittis.\r\nSed auctor dui sit amet magna finibus tempor.");

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {

                    arb.runUpdate_Crypto(arb.API_URL); // If it doesnt update values, check this first.
                    arb.post_crypto();
                    
                    
					
                    left_top_text = "Some text in text pane here\r\n\r\nLorem ipsum dolor sit amet,\nconsectetur adipiscing elit.";
                    price_left_top.setText(left_top_text);
					
					
					crypto = "Lorem ipsum dolor sit amet, \r\nconsectetur adipiscing elit.\r\n Sed pellentesque tellus \r\ntellus, quis volutpat tortor\r\n imperdiet id. Vestibulum in\r\nquam nec lorem dictum \r\nlaoreet consequat sed dui. ";
					cryptoText.setText(crypto);
                } catch (IOException | Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					arb.mathsStuff();
                    arb.print_arb_calc_result();
					
                    arbLorem_1 = "Lorem Ipsum\r\n~~~~~~~~~~\r\n\r\nLorem ipsum dolor sit amet, consect\r\netur adipiscing elit.\r\nSed sed orci congue, dictum eros eget, gravida velit.\r\nQuisque pretium nibh at ligula cursus commodo.\r\nMaecenas bibendum enim sit amet diam hendrerit interdum.\r\nMauris sit amet nunc sed augue porttitor fringilla.\r\n";
                	Lorem_1.setText(arbLorem_1);
					
                	arbLorem_2 = ("Lorem Ipsum\r\n~~~~~~~~~~~~~\r\n\r\n    Proin non lorem vitae magna tincidunt efficitur.\r\n    Donec ultricies diam in vulputate consequat.\r\n    Suspendisse at nisl maximus, tempor urna eu, fermentum turpis.\r\n Fusce viverra nisl at gravida lobortis.\r\nNulla at felis ornare, placerat nulla id, aliquet tortor.\r\n");
                	Lorem_2.setText(arbLorem_2);
					
					
                	arbLorem_3 = ("Lorem Ipsum\r\n~~~~~~~~~~~~~\r\n\r\nEtiam pharetra justo quis maximus convallis.\r\nProin congue risus sed augue malesuada posuere.\r\nSed aliquam libero vel convallis sagittis.\r\nSed auctor dui sit amet magna finibus tempor.");
                	Lorem_3.setText(arbLorem_3);
				}
            }
        };
        Timer timer = new Timer(7000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        Thread.sleep(5000);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		EventQueue.getCurrentEvent();
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1121, 765);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setForeground(Color.CYAN);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.DARK_GRAY);
		panel_1.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_1.setBackground(Color.BLACK);
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(panel_1);
		
		
		price_left_top.setForeground(new Color(255, 255, 255));
		price_left_top.setBackground(new Color(0, 0, 0));
		price_left_top.setEditable(false);
		price_left_top.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		price_left_top.setText(left_top_text);
		panel_1.add(price_left_top);
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.setForeground(Color.DARK_GRAY);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(Color.BLACK);
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		panel.add(panel_3);
		
		
		cryptoText.setForeground(new Color(255, 255, 255));
		cryptoText.setBackground(new Color(0, 0, 0));
		cryptoText.setFont(new Font("Tahoma", Font.BOLD, 11));
		cryptoText.setEditable(false);
		cryptoText.setText(crypto);
		panel_3.add(cryptoText);
		
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(240, 255, 255), new Color(240, 255, 255), new Color(240, 255, 255), new Color(240, 255, 255)));
		panel_4.setBackground(Color.BLACK);
		contentPane.add(panel_4, BorderLayout.CENTER);
		
		
		Lorem_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		Lorem_1.setForeground(Color.WHITE);
		Lorem_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		Lorem_1.setBackground(Color.BLACK);
		Lorem_1.setEditable(false);
		Lorem_1.setText(arbLorem_1);
		panel_4.add(Lorem_1);
		
		
		Lorem_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		Lorem_2.setForeground(Color.WHITE);
		Lorem_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		Lorem_2.setBackground(Color.BLACK);
		Lorem_2.setEditable(false);
		Lorem_2.setText(arbLorem_2);
		panel_4.add(Lorem_2);
		
		
		Lorem_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		Lorem_3.setForeground(Color.WHITE);
		Lorem_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		Lorem_3.setEditable(false);
		Lorem_3.setBackground(Color.BLACK);
		Lorem_3.setText(arbLorem_3);
		panel_4.add(Lorem_3);
	}
}
