import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelAutenticacao extends JFrame {
    private JPanel PainelAutentic;
    private JPanel p1;
    private JTextField Tf_Aut_Email;
    private JButton CANCELARButton;
    private JButton OKButton;
    private JButton Btn_Env_Email;
    private JTextField Tf_Aut_Celular;
    private JTextField Tf_Aut_Google;
    private JButton Btn_Env_Celular;
    private JButton Btn_Env_Google;
    private JLabel Lb_Cliente;

    public PainelAutenticacao(JFrame parent ,String telefone, String email, Integer ID,String nome) {
        setTitle("Autenticação");
        setContentPane(PainelAutentic);
        setMinimumSize(new Dimension(400, 500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email, c_email, celular, c_celular, google, c_google;
                email = "1234";
                celular = "1234";
                google = "1234";

                c_email = Tf_Aut_Email.getText();
                c_celular = Tf_Aut_Celular.getText();
                c_google = Tf_Aut_Google.getText();


                if (c_email.equals(email) && c_celular.equals(celular) && c_google.equals(google)) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Login Bem Sucedido");
                    PainelPrincipal painelPrincipal = new PainelPrincipal(null,ID,nome);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro na Autenticação");
                }
            }
        });
        User user = new User();

        Btn_Env_Email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(null, nome+ " enviamos o Código para seu email " + "\nEmail: " + email);
            }
        });
        Btn_Env_Celular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(null, nome+" enviamos um Código seu celular " + "\nCelular: " + telefone);
            }
        });
        Btn_Env_Google.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, " Olá "+nome +
                        "\nAbra o Google Authenticator do seu celular "+
                        "\nCaso não tenha entre em contato com nosso Suporte ");
            }
        });
        CANCELARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                dispose();
                PainelLogin painelLogin = new PainelLogin(null);

            }
        });
        setVisible(true);
    }
}





