package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Funcionario;
import utilitario.Conectar;

/**
 *
 * @author Gustavo
 */
public class FuncionarioDAO {

    public void cadastrar(Funcionario r) {
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO funcionario (nome,cpf,email,telefone,dataadmissao,senha, perfil) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, r.getNome());
            smt.setString(2, r.getCpf());
            smt.setString(3, r.getEmail());
            smt.setString(4, r.getTelefone());
            smt.setString(5, r.getDataadmissao());
            smt.setString(6, r.getSenha());
            smt.setString(7, r.getPerfil());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o funcionário");
        }
    }

    public void atualizar(Funcionario r) {
        Connection con = Conectar.getConectar();
        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, email = ?, telefone = ?, dataadmissao = ?, senha = ?, perfil = ? WHERE id_funcionario = ?";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, r.getNome());
            smt.setString(2, r.getCpf());
            smt.setString(3, r.getEmail());
            smt.setString(4, r.getTelefone());
            smt.setString(5, r.getDataadmissao());
            smt.setString(6, r.getSenha());
            smt.setString(7, r.getPerfil());
            smt.setInt(8, r.getId_funcionario());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o funcionário");
        }

    }

    public void excluir(Funcionario r) {
        Connection con = Conectar.getConectar();
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o funcionário " + r.getNome(), "Exclusão", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, r.getId_funcionario());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o funcionário");
            }
        }
    }
    
    public List<Funcionario> listarTodos(){
        Connection con = Conectar.getConectar();
        List<Funcionario> listaFuncionario = new ArrayList<>();
        String sql = "SELECT * FROM funcionario ORDER BY nome";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet resultado = smt.executeQuery();
            while(resultado.next()){
                Funcionario f = new Funcionario();
                f.setId_funcionario(resultado.getInt("id_funcionario"));
                f.setNome(resultado.getString("nome"));
                f.setCpf(resultado.getString("cpf"));
                f.setEmail(resultado.getString("email"));
                f.setTelefone(resultado.getString("telefone"));
                f.setDataadmissao(resultado.getString("dataadmissao"));
                f.setSenha(resultado.getString("senha"));
                f.setPerfil(resultado.getString("perfil"));
                listaFuncionario.add(f);
            }
            smt.close();
            con.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar os funcionários");
        }
        return listaFuncionario;
    }
    
    public Funcionario login(String cpf,String senha){
        Connection con = Conectar.getConectar();
        Funcionario f = new Funcionario();
        String sql = "SELECT * FROM funcionario WHERE cpf = ? and senha = ? ";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, cpf);
            smt.setString(2, senha);
            ResultSet resultado = smt.executeQuery();
            resultado.next();
            if(resultado.getInt("id_funcionario") > 0){
                f.setId_funcionario(resultado.getInt("id_funcionario"));
                f.setNome(resultado.getString("nome"));
                f.setCpf(resultado.getString("cpf"));
                f.setEmail(resultado.getString("email"));
                f.setTelefone(resultado.getString("telefone"));
                f.setDataadmissao(resultado.getString("dataadmissao"));
                f.setSenha(resultado.getString("senha"));
                f.setPerfil(resultado.getString("perfil"));
            }else{
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto!");
            }
        }catch(Exception ex){
            //JOptionPane.showMessageDialog(null, "Erro ao buscar o funcionário "+ex.getMessage());
        }
        return f;
    }
}
