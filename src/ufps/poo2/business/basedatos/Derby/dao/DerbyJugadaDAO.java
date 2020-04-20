package ufps.poo2.business.basedatos.Derby.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ufps.poo2.business.basedatos.Derby.dto.JugadaDTO;
import ufps.poo2.business.basedatos.interfaz.IJugadaDAO;

public class DerbyJugadaDAO implements IJugadaDAO {

	private Connection conn;
//	private String DRIVER = "com.mysql.jdbc.Driver";
	private String DBURL = "jdbc:derby://localhost:1527/PROYECTOPOO2;user=EQUIPOTIMBIRICHI;password=1234";
	private String USER = "EQUIPOTIMBIRICHI";
	private String PASS = "1234";
	private boolean keepOpenConn;
    
    public DerbyJugadaDAO(boolean keepOpenConn) {
    	this.keepOpenConn = keepOpenConn;
    	
    	try{
//            Class.forName(DRIVER).newInstance();
            //Get a connection
            conn = DriverManager.getConnection(DBURL, USER, PASS); 
            System.out.println("Conexion establecida");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
	
    
	public boolean insertar(JugadaDTO dto) throws Exception {
		PreparedStatement stmt = null;
        boolean exito = false;
        try {
            stmt = conn.prepareStatement("INSERT INTO JUGADAS "
                    + "(NOMBRE, COORDENADA, , NUMERO) values (?, ?, ?)");
            stmt.setString(1, dto.getMyJ().getNombre());
            stmt.setString(2, dto.getCoordenada());
            stmt.setString(3, dto.getNumero()+"");
            
            int total = stmt.executeUpdate();
            if(total>0)
                exito = true;                        
            stmt.close();            
        } finally {
        	if(!keepOpenConn){
	            if (conn != null) {
	                conn.close();
	            }
        	}
        }
        
        return exito;
	}
//
//	@Override
//	public boolean eliminar(JugadaDTO dto) throws Exception {
//		PreparedStatement stmt = null;
//        boolean exito = false;
//        try {
//            stmt = conn.prepareStatement("DELETE FROM CARRO "
//                    + "WHERE PLACA=?");
//            stmt.setString(1, dto.getPlaca());
//            int total = stmt.executeUpdate();
//            if(total>0)
//                exito = true;                        
//            stmt.close();            
//        } finally {
//        	if(!keepOpenConn){
//	            if (conn != null) {
//	                conn.close();
//	            }
//        	}
//        }
//        
//        return exito;
//	}
//
//	@Override
//	public List<JugadaDTO> listar() throws Exception {
//		List<JugadaDTO> lista = null;
//
//        try {
//
//            // Este modo se usa para evitar ataque de inyecci�n de SQL
//            // String selectStatement = "SELECT * FROM User WHERE userId = ? ";
//            String selectStatement = "SELECT PLACA, CIUDAD, NOMBREPROP, MARCA, MODELO FROM CARRO";
//            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
//            // Para pasar los par�metros
//            //prepStmt.setString(1, userId);
//
//            ResultSet rs = prepStmt.executeQuery();
//
//            lista = new ArrayList<JugadaDTO>();
//
//            while (rs.next()) {
//            	JugadaDTO dto = new JugadaDTO();
//                
//                dto.setPlaca(rs.getString(1));
//                dto.setCiudad(rs.getString(2));
//                dto.setNombreProp(rs.getString(3));
//                dto.setCiudad(rs.getString(4));
//                dto.setModelo(rs.getString(4));
//                
//                lista.add(dto);
//            }
//
//            rs.close();
//            
//        } catch (Exception e) {
//            lista = null;
//            throw new Exception(e);
//        } finally {
//        	if(!keepOpenConn){
//	            if (conn != null) {
//	                conn.close();
//	            }
//        	}
//        }
//        return lista;
//	}
	
	@Override
	public boolean closeConn() throws Exception {
		if (conn != null) {
            conn.close();
            System.out.println("Conexion cerrada");
        }
		return true;
	}

    @Override
    public boolean insertarJugada(JugadaDTO myJ) throws Exception {
        PreparedStatement stmt = null;
        boolean exito = false;
        try {
            stmt = conn.prepareStatement("INSERT INTO JUGADA "
                    + "(NOMBRE, COORDENADA, NUMERO) values (?, ?, ?)");
            stmt.setString(1, myJ.getMyJ().getNombre());
            stmt.setString(2, myJ.getCoordenada());
            
            stmt.setString(3, myJ.getNumero()+"");
            int total = stmt.executeUpdate();
            if(total>0)
                exito = true;                        
            stmt.close();            
        } finally {
        	if(!keepOpenConn){
	            if (conn != null) {
	                conn.close();
	            }
        	}
        }
        
        return exito;
    }

    @Override
    public ArrayList<String> listarJugadas() throws Exception {
        ArrayList<String> lista = null;

        try {

            // Este modo se usa para evitar ataque de inyecci�n de SQL
            // String selectStatement = "SELECT * FROM User WHERE userId = ? ";
            String selectStatement = "SELECT NUMERO, COORDENADA, JUGADOR FROM JUGADA";
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            // Para pasar los par�metros
            //prepStmt.setString(1, userId);

            ResultSet rs = prepStmt.executeQuery();

            lista = new ArrayList<String>();

            while (rs.next()) {
            	String cad=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
                
                lista.add(cad);
            }

            rs.close();
            
        } catch (Exception e) {
            lista = null;
            throw new Exception(e);
        } finally {
        	if(!keepOpenConn){
	            if (conn != null) {
	                conn.close();
	            }
        	}
        }
        return lista;
    }

}
