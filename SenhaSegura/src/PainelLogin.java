import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PainelLogin extends JDialog {
    private JPanel Painel_Principal;
    private JTextField Tf_Login;
    private JPasswordField Pf_Senha;
    private JButton Btn_Logar;
    private JButton Btn_Cadastrar;

    public PainelLogin(JFrame parent) {
        super(parent);
        setTitle("Senha Segura - Login");
        setContentPane(Painel_Principal);
        setMinimumSize(new Dimension(600, 650));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        Btn_Logar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = Tf_Login.getText();
                String senha = String.valueOf(Pf_Senha.getPassword());

                user= (User) getAuthenticatedUser(email, senha);

                if (user != null) {
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(PainelLogin.this,
                            "Email ou Senha Inválidos",
                            "Erro no Login",
                            JOptionPane.ERROR);
                }
            }
        });
        Btn_Cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cadastrar cadastrar=new Cadastrar(null);
            }
        });
        setVisible(true);
    }

    public User user;
    private Object getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/senhasegura";
        final String USERNAME = "root";
        final String PASSWORD = "";

        Connection conn = Conexao.getConnection();

        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND senha=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.IdUser = resultSet.getInt("IdUser");
                user.nome = resultSet.getString("nome");
                user.sobrenome = resultSet.getString("sobrenome");
                user.email = resultSet.getString("email");
                user.senha = resultSet.getString("senha");
                user.telefone = resultSet.getString("telefone");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        PainelLogin painel1 = new PainelLogin(null);

        User user = painel1.user;
        if (user != null) {
            PainelAutenticacao painelAutenticacao = new PainelAutenticacao(null, user.telefone, user.email, user.IdUser,user.nome);
        } else {
            JOptionPane.showMessageDialog(null, "Autenticação cancelada");
        }
    }
}
