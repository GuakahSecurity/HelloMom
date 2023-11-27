/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mapeamento.Medico;
import utilitario.Conectar;

/**
 *
 * @author Elias
 */
public class MedicoDAO {

    public void cadastrar(Medico m) {
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO medico (nome,email,crm,telefone,especializacao) VALUES (?,?,?,?,?)";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, m.getNome());
            smt.setString(2, m.getEmail());
            smt.setString(3, m.getCrm());
            smt.setString(4, m.getTelefone());
            smt.setString(5, m.getEspecializacao());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            smt.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o médico");
        }
    }

    public void atualizar(Medico m) {
        Connection con = Conectar.getConectar();
        String sql = "UPDATE medico SET nome = ?, email = ?, crm = ?, telefone = ?, especializacao = ? WHERE id_medico = ?";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, m.getNome());
            smt.setString(2, m.getEmail());
            smt.setString(3, m.getCrm());
            smt.setString(4, m.getTelefone());
            smt.setString(5, m.getEspecializacao());
            smt.setInt(6, m.getId_medico());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            smt.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o médico");
        }
    }

    public void excluir(Medico m) {
        Connection con = Conectar.getConectar();
        String sql = "DELETE FROM medico WHERE id_medico = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o médico " + m.getNome(), "Excluir médico", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, m.getId_medico());
                smt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                smt.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o médico!");
            }
        }
    }

    public List<Medico> listarTodos() {
        Connection con = Conectar.getConectar();
        List<Medico> listaMedico = new ArrayList<>();
        String sql = "SELECT * FROM medico ORDER BY nome";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            ResultSet resultado = smt.executeQuery();
            while(resultado.next()){
                Medico m = new Medico();
                m.setId_medico(resultado.getInt("id_medico"));
                m.setNome(resultado.getString("nome"));
                m.setEmail(resultado.getString("email"));
                m.setCrm(resultado.getString("crm"));
                m.setTelefone(resultado.getString("telefone"));
                m.setEspecializacao(resultado.getString("especializacao"));
                listaMedico.add(m);
            }
            smt.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar os registros");
        }
       return listaMedico;
    }
}
