/*
 * Copyright (C) July 2014 Rafael Aznar
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.goplace.data.specific.implementation;

import com.goplace.data.publicinterface.DataInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.goplace.helper.ExceptionBooster;
import com.goplace.helper.FilterBeanHelper;

public class MysqlDataSpImpl implements DataInterface {

    Connection connection = null;

    public MysqlDataSpImpl(Connection pooledConnection) {
        connection = pooledConnection;
    }

    @Override
    public int removeOne(int intId, String strTabla) throws Exception {
        PreparedStatement oPreparedStatement = null;
        int intResult = 0;
        try {
            String strSQL = "DELETE FROM " + strTabla + " WHERE id = ?";
            oPreparedStatement = (PreparedStatement) connection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, intId);
            intResult = oPreparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":removeOne ERROR removing register: " + ex.getMessage()));
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return intResult;
    }

    @Override
    public int insertOne(String strTabla) throws Exception {

        ResultSet oResultSet;
        java.sql.PreparedStatement oPreparedStatement = null;
        int id = 0;
        try {
            String strSQL = "INSERT INTO " + strTabla + " (id) VALUES (null) ";
            oPreparedStatement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            int returnLastInsertId = oPreparedStatement.executeUpdate();
            if (returnLastInsertId != -1) {
                oResultSet = oPreparedStatement.getGeneratedKeys();
                oResultSet.next();
                id = oResultSet.getInt(1);
            } else {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":insertOne ERROR inserting register"));
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":insertOne ERROR inserting register: " + ex.getMessage()));
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return id;
    }

    @Override
    public int setNull(int intId, String strTabla, String strCampo) throws Exception {
        PreparedStatement oPreparedStatement = null;
        int intResult = 0;
        try {
            String strSQL = "UPDATE " + strTabla + " SET " + strCampo + " = null WHERE id = ?";
            oPreparedStatement = (PreparedStatement) connection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, intId);
            intResult = oPreparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":setNull ERROR updating register: " + ex.getMessage()));
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return intResult;
    }

    @Override
    public int updateOne(int intId, String strTabla, String strCampo, String strValor) throws Exception {
        int intResult = 0;
        PreparedStatement oPreparedStatement = null;
        try {
            String strSQL = "UPDATE " + strTabla + " SET " + strCampo + " = '" + strValor + "' WHERE id = ?";
            oPreparedStatement = (PreparedStatement) connection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, intId);
            intResult = oPreparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":updateOne ERROR updating register: " + ex.getMessage()));
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return intResult;
    }

    @Override
    public String getId(String strTabla, String strCampo, String strValor) throws Exception {
        String strResult = null;
        Statement oStatement = null;
        ResultSet oResultSet;
        try {
            oStatement = (Statement) connection.createStatement();
            String strSQL = "SELECT id FROM " + strTabla + " WHERE " + strCampo + "='" + strValor + "'";
            oResultSet = oStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                strResult = oResultSet.getString("id");
            } else {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getId ERROR: ID not exists"));
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getId ERROR: Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return strResult;
    }
    
        public String getRegister(String strRegistro, String strTabla, String strCampo, String strValor) throws Exception {
        String strResult = null;
        Statement oStatement = null;
        ResultSet oResultSet;
        try {
            oStatement = (Statement) connection.createStatement();
            String strSQL = "SELECT " + strRegistro + " FROM " + strTabla + " WHERE " + strCampo + "='" + strValor + "'";
            oResultSet = oStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                strResult = oResultSet.getString(strRegistro);
            } else {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getRegister ERROR: " + strRegistro + " not exists"));
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getRegister ERROR: Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return strResult;
    }
    
    public String getIdByTwoValues(String strTabla, String strCampo1, String strValor1,String strCampo2, String strValor2) throws Exception {
        String strResult = null;
        Statement oStatement = null;
        ResultSet oResultSet;
        try {
            oStatement = (Statement) connection.createStatement();
            String strSQL = "SELECT id FROM " + strTabla + " WHERE " + strCampo1 + "='" + strValor1 + "' AND "+ strCampo2 + "='" + strValor2 + "'";
            oResultSet = oStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                strResult = oResultSet.getString("id");
            } else {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getId ERROR: ID not exists"));
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getId ERROR: Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return strResult;
    }

    @Override
    public String getOne(String strTabla, String strCampo, int id) throws Exception {
        String strResult = null;
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet;
        String strSQL = "";

        if (strTabla.substring(0, 6).equalsIgnoreCase("SELECT")) {
            try {
                strSQL = strTabla;
                strSQL+= " AND id=" + id;
                oPreparedStatement = connection.prepareStatement(strSQL);
                //oPreparedStatement.setInt(1, id);
                oResultSet = oPreparedStatement.executeQuery();
                if (oResultSet.next()) {
                    strResult = oResultSet.getString(strCampo);
                } else {
                    ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getOne ERROR: ID not exists: " + id));
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getOne ERROR: Can't process query: " + ex.getMessage()));
            } finally {
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        } else {
            try {
                //pte cambiar
                strSQL = "SELECT " + strCampo + " FROM " + strTabla + " WHERE id=?";
                oPreparedStatement = connection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, id);
                oResultSet = oPreparedStatement.executeQuery();
                if (oResultSet.next()) {
                    strResult = oResultSet.getString(strCampo);
                } else {
                    ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getOne ERROR: ID not exists: " + id));
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getOne ERROR: Can't process query: " + ex.getMessage()));
            } finally {
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }

        }
        return strResult;
    }

    @Override
    public Boolean existsOne(String strTabla, int id) throws Exception {
        Boolean strOrigenTabla = true;
        if (strTabla.length() >= 6) {
            if (strTabla.substring(0, 6).equalsIgnoreCase("SELECT")) {
                strOrigenTabla = false;
            }
        }
        if (strOrigenTabla) {
            int result = 0;
            Statement oStatement = null;
            try {
                oStatement = (Statement) connection.createStatement();
                String strSQL = "SELECT COUNT(*) FROM " + strTabla + " WHERE 1=1";
                ResultSet rs = oStatement.executeQuery(strSQL);
                if (rs.next()) {
                    result = rs.getInt("COUNT(*)");
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":existsOne ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }

            }
            return (result > 0);
        } else {
            int intResult = 0;
            Statement oStatement = null;
            try {
                oStatement = (Statement) connection.createStatement();
                String strSQL = "SELECT COUNT(*) " + strTabla.substring(strTabla.indexOf("FROM"), strTabla.length());
                ResultSet oResultSet = oStatement.executeQuery(strSQL);
                while (oResultSet.next()) {
                    intResult = oResultSet.getInt("COUNT(*)");
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCountSQL ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }
            }
            return intResult > 0;
        }
    }

    @Override
    public void removeSomeId(String strTabla, ArrayList<Integer> Ids) throws SQLException {
        Statement oStatement = null;
        try {
            Iterator<Integer> iterador = Ids.listIterator();
            while (iterador.hasNext()) {
                oStatement = (Statement) connection.createStatement();
                String strSQL = "DELETE FROM " + strTabla + " WHERE id = " + iterador.next();
                oStatement.executeUpdate(strSQL);
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":removeSomeId ERROR: En la consulta: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
    }

    @Override
    public void removeSomeCondition(String strTabla, String campo, String valor) throws Exception {
        Statement oStatement = null;
        try {
            oStatement = (Statement) connection.createStatement();
            String strSQL = "DELETE FROM " + strTabla + " WHERE " + campo + " like " + valor;
            oStatement.executeUpdate(strSQL);
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":deleteOne ERROR:  Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
    }

    @Override
    public ArrayList<String> getColumnsName(String strTabla) throws Exception {
        Boolean strOrigenTabla = true;
        if (strTabla.length() >= 6) {
            if (strTabla.substring(0, 6).equalsIgnoreCase("SELECT")) {
                strOrigenTabla = false;
            }
        }
        ArrayList<String> vector = null;
        Statement oStatement = null;
        if (strOrigenTabla) {
            try {
                vector = new ArrayList<>();
                oStatement = connection.createStatement();
                String strSQL = "SHOW FULL COLUMNS FROM " + strTabla;
                ResultSet oResultSet = oStatement.executeQuery(strSQL);
                while (oResultSet.next()) {
                    if (oResultSet.getString("Field").length() >= 4) {
                        if (oResultSet.getString("Field").substring(0, 3).equalsIgnoreCase("id_")) {
                            vector.add("obj_" + oResultSet.getString("Field").substring(3));
                        } else {
                            vector.add(oResultSet.getString("Field"));
                        }
                    } else {
                        vector.add(oResultSet.getString("Field"));
                    }
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getColumnsName ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }

            }
            return vector;
        } else {
            vector = new ArrayList<>();
            String strResult = null;
            PreparedStatement oPreparedStatement = null;
            ResultSet oResultSet;
            try {
                oPreparedStatement = connection.prepareStatement(strTabla);
                oResultSet = oPreparedStatement.executeQuery();
                //oResultSet = oStatement.executeQuery(strTabla);
                ResultSetMetaData rsmd = oResultSet.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                for (int contador = 1; contador <= numberOfColumns; contador++) {
                    if (rsmd.getColumnName(contador).length() >= 5) { //los nombres de las tablas como minimo han de tener dos caracteres + el id_ o el set = 5 caracteres
                        if (rsmd.getColumnName(contador).substring(0, 3).equalsIgnoreCase("id_")) {
                            vector.add("obj_" + rsmd.getColumnName(contador).substring(3));
                        } else {
                            vector.add(rsmd.getColumnName(contador));
                        }

                    } else {
                        vector.add(rsmd.getColumnName(contador));
                    }
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getColumnsName ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }

            }
            return vector;

        }
    }

    @Override
    public ArrayList<String> getPrettyColumns(String strTabla) throws Exception {
        Boolean strOrigenTabla = true;
        if (strTabla.length() >= 6) {
            if (strTabla.substring(0, 6).equalsIgnoreCase("SELECT")) {
                strOrigenTabla = false;
            }
        }
        ArrayList<String> vector = null;
        Statement oStatement = null;
        if (strOrigenTabla) {
            try {
                vector = new ArrayList<>();
                oStatement = connection.createStatement();
                //String strSQL = "SELECT id FROM " + strTabla + " WHERE 1=1 ";
                String strSQL = "SHOW FULL COLUMNS FROM " + strTabla;
                ResultSet oResultSet = oStatement.executeQuery(strSQL);
                while (oResultSet.next()) {
                    vector.add(oResultSet.getString("Comment")); //COLUMNS.Comment COLUMN_COMMENT
//                if (desc) {
//                    if (oResultSet.getString("COLUMN_NAME").length() >= 4) {
//                        if (oResultSet.getString("COLUMN_NAME").substring(0, 3).equalsIgnoreCase("id_")) {
//                            vector.add("desc_" + oResultSet.getString("COLUMN_NAME").substring(3));
//                        }
//                    }
//                }
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPrettyColumns ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }

            }
            return vector;
        } else {
            vector = new ArrayList<>();
            String strResult = null;
            PreparedStatement oPreparedStatement = null;
            ResultSet oResultSet;
            try {

                oPreparedStatement = connection.prepareStatement(strTabla);
                oResultSet = oPreparedStatement.executeQuery();
                //oResultSet = oStatement.executeQuery(strTabla);
                ResultSetMetaData rsmd = oResultSet.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                for (int contador = 1; contador <= numberOfColumns; contador++) {
                    if (rsmd.getColumnName(contador).length() >= 5) { //los nombres de las tablas como minimo han de tener dos caracteres + el id_ o el set = 5 caracteres
                        if (rsmd.getColumnName(contador).substring(0, 3).equalsIgnoreCase("id_")) {
                            vector.add(rsmd.getColumnName(contador).substring(3));
                        } else {
                            vector.add(rsmd.getColumnName(contador));
                        }

                    } else {
                        vector.add(rsmd.getColumnName(contador));
                    }
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getColumnsName ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }

            }
            return vector;
        }
    }

    @Override
    public ArrayList<Integer> getPage(String strTabla, int intRegsPerPage, int intPagina, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {
        Boolean strOrigenTabla = true;
        if (strTabla.length() >= 6) {
            if (strTabla.substring(0, 6).equalsIgnoreCase("SELECT")) {
                strOrigenTabla = false;
            }
        }
        if (strOrigenTabla) {

            ArrayList<Integer> vector = null;
            Statement oStatement = null;
            try {
                vector = new ArrayList<>();
                int intOffset;
                oStatement = (Statement) connection.createStatement();
                String strSQL = "SELECT id FROM " + strTabla + " WHERE 1=1 ";
                String strSQLcount = "SELECT COUNT(*) FROM " + strTabla + " WHERE 1=1 ";
                if (alFilter != null) {
                    String strSQLFilter = "";
                    Iterator iterator = alFilter.iterator();
                    while (iterator.hasNext()) {
                        FilterBeanHelper oFilterBean = (FilterBeanHelper) iterator.next();
                        switch (oFilterBean.getFilterOperator()) {
                            case "like":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " LIKE '%" + oFilterBean.getFilterValue() + "%'";
                                break;
                            case "notlike":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " NOT LIKE '%" + oFilterBean.getFilterValue() + "%'";
                                break;
                            case "equals":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " = '" + oFilterBean.getFilterValue() + "'";
                                break;
                            case "notequalto":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " <> '" + oFilterBean.getFilterValue() + "'";
                                break;
                            case "less":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " < " + oFilterBean.getFilterValue() + "";
                                break;
                            case "lessorequal":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " <= " + oFilterBean.getFilterValue() + "";
                                break;
                            case "greater":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " > " + oFilterBean.getFilterValue() + "";
                                break;
                            case "greaterorequal":
                                strSQLFilter += " AND " + oFilterBean.getFilter() + " >= " + oFilterBean.getFilterValue() + "";
                                break;
                        }
                    }
                    strSQL += strSQLFilter;
                    strSQLcount += strSQLFilter;
                }
                //when limit of pages exceed, show last page
                ResultSet oResultSet = oStatement.executeQuery(strSQLcount);
                int intCuenta = 0;
                if (oResultSet.next()) {
                    intCuenta = oResultSet.getInt("COUNT(*)");
                }
                int maxPaginas = new Double(intCuenta / intRegsPerPage).intValue();
                intPagina = Math.min(intPagina - 1, maxPaginas) + 1;
                intOffset = Math.max(((intPagina - 1) * intRegsPerPage), 0);
                //--                        
                if (hmOrder != null) {
                    strSQL += " ORDER BY";
                    for (Map.Entry oPar : hmOrder.entrySet()) {
                        strSQL += " " + oPar.getKey() + " " + oPar.getValue() + ",";
                    }
                    strSQL = strSQL.substring(0, strSQL.length() - 1);
                }
                strSQL += " LIMIT " + intOffset + " , " + intRegsPerPage;
                oResultSet = oStatement.executeQuery(strSQL);
                while (oResultSet.next()) {
                    vector.add(oResultSet.getInt("id"));
                }

            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }
            }
            return vector;
        } else {
            ArrayList<Integer> vector = null;
            Statement oStatement = null;
            try {
                vector = new ArrayList<>();
                int intOffset;
                oStatement = (Statement) connection.createStatement();
                String strSQLcount = "SELECT COUNT(*) " + strTabla.substring(strTabla.indexOf("FROM"), strTabla.length());
                //when limit of pages exceed, show last page
                ResultSet oResultSet = oStatement.executeQuery(strSQLcount);
                int intCuenta = 0;
                if (oResultSet.next()) {
                    intCuenta = oResultSet.getInt("COUNT(*)");
                }
                int maxPaginas = new Double(intCuenta / intRegsPerPage).intValue();
                intPagina = Math.min(intPagina - 1, maxPaginas) + 1;
                intOffset = Math.max(((intPagina - 1) * intRegsPerPage), 0);
                //--                        
                strTabla += " LIMIT " + intOffset + " , " + intRegsPerPage;
                oResultSet = oStatement.executeQuery(strTabla);
                while (oResultSet.next()) {
                    vector.add(oResultSet.getInt("id"));
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPageSQL ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }
            }
            return vector;
        }
    }

    public ArrayList<Integer> getPageEventos(int id_usuario, int intRegsPerPage, int intPagina, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        ArrayList<Integer> eventos = null;
        Statement oStatement = null;
        try {
            eventos = new ArrayList<>();
            int intOffset;
            oStatement = (Statement) connection.createStatement();
            String strSQL = "SELECT distinct p.* FROM publicacion p INNER JOIN amistad a WHERE p.id_usuario = a.id_usuario_2 AND a.id_usuario_1 = " + id_usuario + " ORDER BY p.fechapub ASC ";
            String strSQLcount = "SELECT COUNT(*) from publicacion p inner join amistad a where p.id_usuario = a.id_usuario_2 and a.id_usuario_1 = " + id_usuario + " ";
            // select p.* from publicacion p inner join amigo a where p.id_usuario = a.id_usuario_2 and a.id_usuario_1 = 2 ORDER BY `fechacreacion` DESC
            if (alFilter != null) {
                String strSQLFilter = "";
                Iterator iterator = alFilter.iterator();
                while (iterator.hasNext()) {
                    FilterBeanHelper oFilterBean = (FilterBeanHelper) iterator.next();
                    switch (oFilterBean.getFilterOperator()) {
                        case "like":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "notlike":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " NOT LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "equals":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " = '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "notequalto":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " <> '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "less":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " < " + oFilterBean.getFilterValue() + "";
                            break;
                        case "lessorequal":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " <= " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greater":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " > " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greaterorequal":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " >= " + oFilterBean.getFilterValue() + "";
                            break;
                    }
                }
                strSQL += strSQLFilter;
                strSQLcount += strSQLFilter;
            }
            //when limit of pages exceed, show last page
            ResultSet oResultSet = oStatement.executeQuery(strSQLcount);
            int intCuenta = 0;
            if (oResultSet.next()) {
                intCuenta = oResultSet.getInt("COUNT(*)");
            }
            int maxPaginas = new Double(intCuenta / intRegsPerPage).intValue();
            intPagina = Math.min(intPagina - 1, maxPaginas) + 1;
            intOffset = Math.max(((intPagina - 1) * intRegsPerPage), 0);
            //--                        
            strSQL += " LIMIT " + intOffset + " , " + intRegsPerPage;
            oResultSet = oStatement.executeQuery(strSQL);
            while (oResultSet.next()) {
                eventos.add(oResultSet.getInt("id"));
            }

        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR:  Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return eventos;
    }

        public ArrayList<Integer> getPageComentarios(int id_publicacion, int intRegsPerPage, int intPagina, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        ArrayList<Integer> comentarios = null;
        Statement oStatement = null;
        try {
            comentarios = new ArrayList<>();
            int intOffset;
            oStatement = (Statement) connection.createStatement();
            String strSQL = "SELECT distinct c.* FROM comentario c INNER JOIN publicacion p WHERE c.id_publicacion = p.id AND p.id = " + id_publicacion + " ORDER BY c.fechacomentario ASC ";
            String strSQLcount = "SELECT COUNT(*) FROM comentario c INNER JOIN publicacion p WHERE c.id_publicacion = p.id AND p.id = " + id_publicacion + " ";
            // select p.* from publicacion p inner join amigo a where p.id_usuario = a.id_usuario_2 and a.id_usuario_1 = 2 ORDER BY `fechacreacion` DESC
            if (alFilter != null) {
                String strSQLFilter = "";
                Iterator iterator = alFilter.iterator();
                while (iterator.hasNext()) {
                    FilterBeanHelper oFilterBean = (FilterBeanHelper) iterator.next();
                    switch (oFilterBean.getFilterOperator()) {
                        case "like":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "notlike":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " NOT LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "equals":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " = '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "notequalto":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " <> '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "less":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " < " + oFilterBean.getFilterValue() + "";
                            break;
                        case "lessorequal":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " <= " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greater":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " > " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greaterorequal":
                            strSQLFilter += " AND " + oFilterBean.getFilter() + " >= " + oFilterBean.getFilterValue() + "";
                            break;
                    }
                }
                strSQL += strSQLFilter;
                strSQLcount += strSQLFilter;
            }
            //when limit of pages exceed, show last page
            ResultSet oResultSet = oStatement.executeQuery(strSQLcount);
            int intCuenta = 0;
            if (oResultSet.next()) {
                intCuenta = oResultSet.getInt("COUNT(*)");
            }
            int maxPaginas = new Double(intCuenta / intRegsPerPage).intValue();
            intPagina = Math.min(intPagina - 1, maxPaginas) + 1;
            intOffset = Math.max(((intPagina - 1) * intRegsPerPage), 0);
            //--                        
            strSQL += " LIMIT " + intOffset + " , " + intRegsPerPage;
            oResultSet = oStatement.executeQuery(strSQL);
            while (oResultSet.next()) {
                comentarios.add(oResultSet.getInt("id"));
            }

        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR:  Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return comentarios;
    }
    
    @Override
    public int getPages(String strTabla, int intRegsPerPage, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        Boolean strOrigenTabla = true;
        if (strTabla.length() >= 6) {
            if (strTabla.substring(0, 6).equalsIgnoreCase("SELECT")) {
                strOrigenTabla = false;
            }
        }
        if (strOrigenTabla) {
            int intResult = 0;
            Statement oStatement = null;
            try {
                oStatement = (Statement) connection.createStatement();
                String strSQL = "SELECT count(*) FROM " + strTabla + " WHERE 1=1";
                if (alFilter != null) {
                    Iterator iterator = alFilter.iterator();
                    while (iterator.hasNext()) {
                        FilterBeanHelper oFilterBean = (FilterBeanHelper) iterator.next();
                        switch (oFilterBean.getFilterOperator()) {
                            case "like":
                                strSQL += " AND " + oFilterBean.getFilter() + " LIKE '%" + oFilterBean.getFilterValue() + "%'";
                                break;
                            case "notlike":
                                strSQL += " AND " + oFilterBean.getFilter() + " NOT LIKE '%" + oFilterBean.getFilterValue() + "%'";
                                break;
                            case "equals":
                                strSQL += " AND " + oFilterBean.getFilter() + " = '" + oFilterBean.getFilterValue() + "'";
                                break;
                            case "notequalto":
                                strSQL += " AND " + oFilterBean.getFilter() + " <> '" + oFilterBean.getFilterValue() + "'";
                                break;
                            case "less":
                                strSQL += " AND " + oFilterBean.getFilter() + " < " + oFilterBean.getFilterValue() + "";
                                break;
                            case "lessorequal":
                                strSQL += " AND " + oFilterBean.getFilter() + " <= " + oFilterBean.getFilterValue() + "";
                                break;
                            case "greater":
                                strSQL += " AND " + oFilterBean.getFilter() + " > " + oFilterBean.getFilterValue() + "";
                                break;
                            case "greaterorequal":
                                strSQL += " AND " + oFilterBean.getFilter() + " >= " + oFilterBean.getFilterValue() + "";
                                break;
                        }

                    }
                }
                ResultSet oResultSet = oStatement.executeQuery(strSQL);
                while (oResultSet.next()) {
                    intResult = oResultSet.getInt("COUNT(*)") / intRegsPerPage;
                    if ((oResultSet.getInt("COUNT(*)") % intRegsPerPage) > 0) {
                        intResult++;
                    }
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }
            }
            return intResult;
        } else {

            int intResult = 0;
            Statement oStatement = null;
            try {
                oStatement = (Statement) connection.createStatement();
                strTabla = "SELECT COUNT(*) " + strTabla.substring(strTabla.indexOf("FROM"), strTabla.length());

                ResultSet oResultSet = oStatement.executeQuery(strTabla);
                while (oResultSet.next()) {
                    intResult = oResultSet.getInt("COUNT(*)") / intRegsPerPage;
                    if ((oResultSet.getInt("COUNT(*)") % intRegsPerPage) > 0) {
                        intResult++;
                    }
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPagesSQL ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }
            }
            return intResult;
        }
    }

    public int getPagesEventos(int id_usuario, int intRegsPerPage, ArrayList<FilterBeanHelper> alFilter) throws Exception {

        int intResult = 0;
        Statement oStatement = null;
        try {
            oStatement = (Statement) connection.createStatement();
            String strSQL = "SELECT COUNT(*) from publicacion p inner join amistad a where p.id_usuario = a.id_usuario_2 and a.id_usuario_1 = " + id_usuario + " ";
            if (alFilter != null) {
                Iterator iterator = alFilter.iterator();
                while (iterator.hasNext()) {
                    FilterBeanHelper oFilterBean = (FilterBeanHelper) iterator.next();
                    switch (oFilterBean.getFilterOperator()) {
                        case "like":
                            strSQL += " AND " + oFilterBean.getFilter() + " LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "notlike":
                            strSQL += " AND " + oFilterBean.getFilter() + " NOT LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "equals":
                            strSQL += " AND " + oFilterBean.getFilter() + " = '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "notequalto":
                            strSQL += " AND " + oFilterBean.getFilter() + " <> '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "less":
                            strSQL += " AND " + oFilterBean.getFilter() + " < " + oFilterBean.getFilterValue() + "";
                            break;
                        case "lessorequal":
                            strSQL += " AND " + oFilterBean.getFilter() + " <= " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greater":
                            strSQL += " AND " + oFilterBean.getFilter() + " > " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greaterorequal":
                            strSQL += " AND " + oFilterBean.getFilter() + " >= " + oFilterBean.getFilterValue() + "";
                            break;
                    }

                }
            }
            ResultSet oResultSet = oStatement.executeQuery(strSQL);
            while (oResultSet.next()) {
                intResult = oResultSet.getInt("COUNT(*)") / intRegsPerPage;
                if ((oResultSet.getInt("COUNT(*)") % intRegsPerPage) > 0) {
                    intResult++;
                }
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR:  Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return intResult;
    }
    
    public int getPagesComentarios(int id_publicacion, int intRegsPerPage, ArrayList<FilterBeanHelper> alFilter) throws Exception {

        int intResult = 0;
        Statement oStatement = null;
        try {
            oStatement = (Statement) connection.createStatement();
            String strSQL = "SELECT COUNT(*) FROM comentario c INNER JOIN publicacion p WHERE c.id_publicacion = p.id AND p.id = " + id_publicacion + " ";
            if (alFilter != null) {
                Iterator iterator = alFilter.iterator();
                while (iterator.hasNext()) {
                    FilterBeanHelper oFilterBean = (FilterBeanHelper) iterator.next();
                    switch (oFilterBean.getFilterOperator()) {
                        case "like":
                            strSQL += " AND " + oFilterBean.getFilter() + " LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "notlike":
                            strSQL += " AND " + oFilterBean.getFilter() + " NOT LIKE '%" + oFilterBean.getFilterValue() + "%'";
                            break;
                        case "equals":
                            strSQL += " AND " + oFilterBean.getFilter() + " = '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "notequalto":
                            strSQL += " AND " + oFilterBean.getFilter() + " <> '" + oFilterBean.getFilterValue() + "'";
                            break;
                        case "less":
                            strSQL += " AND " + oFilterBean.getFilter() + " < " + oFilterBean.getFilterValue() + "";
                            break;
                        case "lessorequal":
                            strSQL += " AND " + oFilterBean.getFilter() + " <= " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greater":
                            strSQL += " AND " + oFilterBean.getFilter() + " > " + oFilterBean.getFilterValue() + "";
                            break;
                        case "greaterorequal":
                            strSQL += " AND " + oFilterBean.getFilter() + " >= " + oFilterBean.getFilterValue() + "";
                            break;
                    }

                }
            }
            ResultSet oResultSet = oStatement.executeQuery(strSQL);
            while (oResultSet.next()) {
                intResult = oResultSet.getInt("COUNT(*)") / intRegsPerPage;
                if ((oResultSet.getInt("COUNT(*)") % intRegsPerPage) > 0) {
                    intResult++;
                }
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR:  Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return intResult;
    }
    
    @Override
    public int getCount(String strTabla, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        Boolean strOrigenTabla = true;
        if (strTabla.length() >= 6) {
            if (strTabla.substring(0, 6).equalsIgnoreCase("SELECT")) {
                strOrigenTabla = false;
            }
        }
        if (strOrigenTabla) {
            int intResult = 0;
            Statement oStatement = null;
            try {
                oStatement = (Statement) connection.createStatement();
                String strSQL = "SELECT count(*) FROM " + strTabla + " WHERE 1=1";
                if (alFilter != null) {
                    Iterator iterator = alFilter.iterator();
                    while (iterator.hasNext()) {
                        FilterBeanHelper oFilterBean = (FilterBeanHelper) iterator.next();
                        switch (oFilterBean.getFilterOperator()) {
                            case "like":
                                strSQL += " AND " + oFilterBean.getFilter() + " LIKE '%" + oFilterBean.getFilterValue() + "%'";
                                break;
                            case "notlike":
                                strSQL += " AND " + oFilterBean.getFilter() + " NOT LIKE '%" + oFilterBean.getFilterValue() + "%'";
                                break;
                            case "equals":
                                strSQL += " AND " + oFilterBean.getFilter() + " = '" + oFilterBean.getFilterValue() + "'";
                                break;
                            case "notequalto":
                                strSQL += " AND " + oFilterBean.getFilter() + " <> '" + oFilterBean.getFilterValue() + "'";
                                break;
                            case "less":
                                strSQL += " AND " + oFilterBean.getFilter() + " < " + oFilterBean.getFilterValue() + "";
                                break;
                            case "lessorequal":
                                strSQL += " AND " + oFilterBean.getFilter() + " <= " + oFilterBean.getFilterValue() + "";
                                break;
                            case "greater":
                                strSQL += " AND " + oFilterBean.getFilter() + " > " + oFilterBean.getFilterValue() + "";
                                break;
                            case "greaterorequal":
                                strSQL += " AND " + oFilterBean.getFilter() + " >= " + oFilterBean.getFilterValue() + "";
                                break;
                        }
                    }
                }
                ResultSet oResultSet = oStatement.executeQuery(strSQL);
                while (oResultSet.next()) {
                    intResult = oResultSet.getInt("COUNT(*)");
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }
            }
            return intResult;
        } else {
            int intResult = 0;
            Statement oStatement = null;
            try {
                oStatement = (Statement) connection.createStatement();
                strTabla = "SELECT COUNT(*) " + strTabla.substring(strTabla.indexOf("FROM"), strTabla.length());
                ResultSet oResultSet = oStatement.executeQuery(strTabla);
                while (oResultSet.next()) {
                    intResult = oResultSet.getInt("COUNT(*)");
                }
            } catch (SQLException ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCountSQL ERROR:  Can't process query: " + ex.getMessage()));
            } finally {
                if (oStatement != null) {
                    oStatement.close();
                }
            }
            return intResult;
        }
    }

    public int agregarDato(String tabla, int valor1, int valor2) throws Exception {

        ResultSet oResultSet;
        java.sql.PreparedStatement oPreparedStatement = null;
        int id = 0;
        
        String strSQL = "";
        
        try {
            if(tabla == "amistad"){
                strSQL = "INSERT INTO `amistad` (`id`, `id_usuario_1`, `id_usuario_2`) VALUES (NULL, '" + valor1 + "', '" + valor2 + "'); ";
            } else if(tabla == "asistencia"){
                strSQL = "INSERT INTO `asistencia` (`id`, `id_usuario`, `id_publicacion`) VALUES (NULL, '" + valor1 + "', '" + valor2 + "'); ";   
            }
            oPreparedStatement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            int returnLastInsertId = oPreparedStatement.executeUpdate();
            if (returnLastInsertId != -1) {
                oResultSet = oPreparedStatement.getGeneratedKeys();
                oResultSet.next();
                id = oResultSet.getInt(1);
            } else {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":agregarDato ERROR inserting register"));
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":agregarDato ERROR inserting register: " + ex.getMessage()));
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return id;
    }

    public int removeDato(String tabla, int valor1, int valor2) throws Exception {

        java.sql.PreparedStatement oPreparedStatement = null;
        int intResult = 0;
        String strSQL = "";
        try {
            if(tabla == "amistad"){
                strSQL = "DELETE FROM `amistad` WHERE `id_usuario_1` = " + valor1 + " AND `id_usuario_2` = " + valor2;
            } else if(tabla == "asistencia"){
                strSQL = "DELETE FROM `asistencia` WHERE `id_usuario` = " + valor1 + " AND `id_publicacion` = " + valor2;
            }
            oPreparedStatement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            intResult = oPreparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":removeDato ERROR inserting register: " + ex.getMessage()));
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return intResult;
    }

    public int existeDato(String tabla, int valor1, int valor2) throws Exception {

        int intResult = 0;
        Statement oStatement = null;
        String strSQL = "";
        try {
            oStatement = (Statement) connection.createStatement();
            
            if(tabla == "amistad"){
                strSQL = "SELECT COUNT(id) FROM `amistad` WHERE `id_usuario_1` = " + valor1 + " AND `id_usuario_2` = " + valor2;
            } else if(tabla == "asistencia"){
                strSQL = "SELECT COUNT(id) FROM `asistencia` WHERE `id_usuario` = " + valor1 + " AND `id_publicacion` = " + valor2;
            }
            ResultSet oResultSet = oStatement.executeQuery(strSQL);
            while (oResultSet.next()) {
                intResult = oResultSet.getInt("COUNT(id)");
            }
        } catch (SQLException ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":existeDato ERROR:  Can't process query: " + ex.getMessage()));
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return intResult;
    }
    


}
