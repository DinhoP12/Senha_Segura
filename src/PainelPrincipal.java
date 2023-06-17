import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.sql.*;

public class PainelPrincipal extends JDialog {
    private JPanel Painel1;
    private JTable Tb_Consulta;
    private JButton BtnAdicionar;
    private JButton Btn_Gmail;
    private JButton Btn_Linkedin;
    private JButton Btn_Netflix;
    private JButton Btn_Meta;
    private JButton Btn_Spotify;
    private JButton Btn_github;
    private JLabel Lb_Nome;
    private JButton btnExcluir;

    public PainelPrincipal(JFrame parent, Integer ID, String nome) {
        setTitle("Senha Segura");
        setContentPane(Painel1);
        setMinimumSize(new Dimension(550, 700));
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        Lb_Nome.setText("Olá " + nome);

        String url = "jdbc:mysql://localhost:3306/senhasegura";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT Email, Senha, Descricao FROM titulo WHERE Id_User = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Email");
            model.addColumn("Senha");
            model.addColumn("Descrição");

            while (resultSet.next()) {
                Object[] rowData = new Object[3];
                rowData[0] = resultSet.getString("Email");
                rowData[1] = resultSet.getString("Senha");
                rowData[2] = resultSet.getString("Descricao");
                model.addRow(rowData);
            }

            Tb_Consulta.setModel(model);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Tb_Consulta.getSelectedRow();
                if (selectedRow >= 0) {
                    String email = (String) Tb_Consulta.getValueAt(selectedRow, 0);
                    String senha = (String) Tb_Consulta.getValueAt(selectedRow, 1);
                    String descricao = (String) Tb_Consulta.getValueAt(selectedRow, 2);

                    DefaultTableModel model = (DefaultTableModel) Tb_Consulta.getModel();
                    model.removeRow(selectedRow);

                    String deleteQuery = "DELETE FROM titulo WHERE Email = ? AND Senha = ? AND Descricao = ?";
                    try (Connection connection = DriverManager.getConnection(url, username, password);
                         PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                        statement.setString(1, email);
                        statement.setString(2, senha);
                        statement.setString(3, descricao);
                        statement.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(Painel1, "Selecione um campo para excluir.");
                }
            }
        });

        Btn_Gmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://www.google.com/intl/pt-BR/gmail/about/");
                    Desktop.getDesktop().browse(link);
                } catch (Exception erro) {
                    System.out.println(erro);
                }
            }
        });

        Btn_Linkedin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://br.linkedin.com/");
                    Desktop.getDesktop().browse(link);

                } catch (Exception erro) {
                    System.out.println(erro);
                }

            }
        });
        Btn_Spotify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://open.spotify.com/search");
                    Desktop.getDesktop().browse(link);
                } catch (Exception erro) {
                    System.out.println(erro);
                }

            }
        });
        Btn_Meta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://pt-br.facebook.com/login");
                    Desktop.getDesktop().browse(link);
                } catch (Exception erro) {
                    System.out.println(erro);
                }

            }
        });
        Btn_Netflix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://www.netflix.com/br/login");
                    Desktop.getDesktop().browse(link);
                } catch (Exception erro) {
                    System.out.println(erro);
                }

            }
        });
        Btn_github.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI link = new URI("https://github.com/login");
                    Desktop.getDesktop().browse(link);
                } catch (Exception erro) {
                    System.out.println(erro);
                }
            }
        });
        BtnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = JOptionPane.showInputDialog(PainelPrincipal.this, "Digite o email:");
                String senha = JOptionPane.showInputDialog(PainelPrincipal.this, "Digite a senha:");
                String descricao = JOptionPane.showInputDialog(PainelPrincipal.this, "Digite a descrição:");

                DefaultTableModel model = (DefaultTableModel) Tb_Consulta.getModel();
                Object[] rowData = {email, senha, descricao};
                model.addRow(rowData);

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO titulo (Email, Senha, Descricao, Id_User) VALUES (?, ?, ?, ?)");
                    statement.setString(1, email);
                    statement.setString(2, senha);
                    statement.setString(3, descricao);
                    statement.setInt(4, ID);
                    statement.executeUpdate();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        setVisible(true);
    }
}
