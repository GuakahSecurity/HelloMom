package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Paciente;
import utilitario.Conectar;

public class PacienteDAO {

    public void cadastrar(Paciente p) {
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO paciente (nome,cpf,email,datanasc,telefone,sexo,senha) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setString(4, p.getDatanasc());
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getSexo());
            smt.setString(7,p.getSenha());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        }
    }

    public void atualizar(Paciente p) {
        Connection con = Conectar.getConectar();
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, email = ?, datanasc = ?, telefone = ?, sexo = ?, senha = ? WHERE id_paciente = ?";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setString(4, p.getDatanasc());
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getSexo());
            smt.setString(7,p.getSenha());
            smt.setInt(8, p.getId_pacinte());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro!");
        }
    }

    public void excluir(Paciente p) {
        Connection con = Conectar.getConectar();
        String sql = "DELETE FROM paciente WHERE id_paciente = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o paciente "
                + p.getNome() + "?", "Exluir", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, p.getId_pacinte());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o registro "+ex.getMessage());
            }
        }
    }
    
    public List<Paciente> listarTodos(){
        Connection con = Conectar.getConectar();
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER BY nome";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet resultado = smt.executeQuery();
            while(resultado.next()){
                Paciente p = new Paciente();
                p.setId_pacinte(resultado.getInt("id_paciente"));
                p.setNome(resultado.getString("nome"));
                p.setCpf(resultado.getString("cpf"));
                p.setEmail(resultado.getString("email"));
                p.setDatanasc(resultado.getString("datanasc"));
                p.setTelefone(resultado.getString("telefone"));
                p.setSexo(resultado.getString("sexo"));
                p.setSenha(resultado.getString("senha"));
                lista.add(p);
            }
            smt.close();
            con.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar os registros");
        }
        return lista;
    }
    
    public Paciente login(String cpf,String senha){
        Connection con = Conectar.getConectar();
        Paciente p = new Paciente();
        String sql = "SELECT * FROM paciente WHERE cpf = ? and senha = md5(?)";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, cpf);
            smt.setString(2, senha);
            ResultSet resultado = smt.executeQuery();
            resultado.next();
            if(resultado.getInt("id_paciente") > 0){
                p.setId_pacinte(resultado.getInt("id_paciente"));
                p.setNome(resultado.getString("nome"));
                p.setCpf(resultado.getString("cpf"));
                p.setEmail(resultado.getString("email"));
                p.setDatanasc(resultado.getString("datanasc"));
                p.setTelefone(resultado.getString("telefone"));
                p.setSexo(resultado.getString("sexo"));
                p.setSenha(resultado.getString("senha"));
            }else{
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto!");
            }
        }catch(Exception ex){
            //JOptionPane.showMessageDialog(null, "Erro ao buscar o funcionário "+ex.getMessage());
        }
        return p;
    }
    
}
