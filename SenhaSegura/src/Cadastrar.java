import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Cadastrar extends JDialog{
    private JPanel panel1;
    private JTextField Tf_Nv_Nome;
    private JLabel Lb_Cliente;
    private JPasswordField Ps_Nv_Senha;
    private JButton REGISTRARButton;
    private JButton CANCELARButton;
    private JTextField Tf_Nv_Sobrenome;
    private JTextField Tf_Nv_Email;
    private JTextField Tf_Nv_Celular;
    private JPasswordField Ps_Nv_ConSenha;

    public Cadastrar(JFrame parent) {
        super(parent);
        setTitle("Novo Usuario");
        setContentPane(panel1);
        setMinimumSize(new Dimension(400, 500));
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);


        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarUsuario();
            }
        });
        CANCELARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void RegistrarUsuario() {
        String nome = Tf_Nv_Nome.getText();
        String sobrenome = Tf_Nv_Sobrenome.getText();
        String email = Tf_Nv_Email.getText();
        String celular = Tf_Nv_Celular.getText();
        String senha = String.valueOf(Ps_Nv_Senha.getPassword());
        String confirmasenha = String.valueOf(Ps_Nv_ConSenha.getPassword());

        if(nome.isEmpty() || sobrenome.isEmpty()|| email.isEmpty()|| celular.isEmpty()||senha.isEmpty()||confirmasenha.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos",
                    "Campo Vazio",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!senha.equals(confirmasenha)){
            JOptionPane.showMessageDialog(this,
                    "Senhas estão diferente",
                    "Senha Diferente",JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = AdicionarUserDB(nome,sobrenome,email,celular,senha);

        if(user!= null){
            JOptionPane.showMessageDialog(this,
                    "Cadastrado com Sucesso",
                    "Sucesso",JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao Cadastrar",
                    "Tente Novamente",JOptionPane.ERROR_MESSAGE);
        }
    }
public User user;
    private User AdicionarUserDB(String nome,String sobrenome, String email, String celular,String senha){
        User user = null;

        try{
            //  Connection comm = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Connection comm = Conexao.getConnection();
            Statement stmt = comm.createStatement();

            String sql ="INSERT INTO users (nome, sobrenome, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = comm.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, sobrenome);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, senha);
            preparedStatement.setString(5, celular);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                user = new User();
                user.nome=nome;
                user.sobrenome=sobrenome;
                user.email=email;
                user.senha=senha;
                user.telefone=celular;
            }
            //stmt.close();
            //comm.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }
}

