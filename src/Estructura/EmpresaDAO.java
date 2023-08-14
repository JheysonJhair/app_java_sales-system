package Estructura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Jhair
 */

public class EmpresaDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Datos Empresa
    public Empresa BuscarDatos(){
        Empresa empresa = new Empresa();
        String sql = "SELECT * FROM tEmpresa";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                empresa.setId(rs.getInt("id"));
                empresa.setRuc(rs.getString("ruc"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setTelefono(rs.getString("telefono"));
                empresa.setDireccion(rs.getString("direccion"));
                empresa.setMensaje(rs.getString("mensaje"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return empresa;
    }
    
    //Modificar datos Empresa
    public boolean ModificarDatos(Empresa empresa){
       String sql = "UPDATE tEmpresa SET ruc = ?, nombre = ?, telefono = ?, direccion = ?, mensaje = ? WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, empresa.getRuc());
           ps.setString(2, empresa.getNombre());
           ps.setString(3, empresa.getTelefono());
           ps.setString(4, empresa.getDireccion());
           ps.setString(5, empresa.getMensaje());
           ps.setInt(6, empresa.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
}
