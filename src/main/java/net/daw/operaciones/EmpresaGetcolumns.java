/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operaciones;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.dao.EmpresaDao;
import net.daw.helper.Conexion;

/**
 *
 * @author AntonioNP
 */
public class EmpresaGetcolumns implements GenericOperation{
     @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //trabajando en la rama cpp
        ArrayList<String> alColumns = null;
        try {
            int cpp;
            if(request.getParameter("cpp") == null){
                cpp = 2;
            } else {
                cpp = Integer.parseInt(request.getParameter("cpp"));
            }
            EmpresaDao oEmpresaDAO = new EmpresaDao(Conexion.getConection());
            alColumns = oEmpresaDAO.getColumnsNames(cpp);
            String data = new Gson().toJson(alColumns);
            data = "{\"data\":" + data + "}";
            return data;
        } catch (Exception e) {
            throw new ServletException("EmpresaGetcolumnsJson: View Error: " + e.getMessage());
        }
    }
}
