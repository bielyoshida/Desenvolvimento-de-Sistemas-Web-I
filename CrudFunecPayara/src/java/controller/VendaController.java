/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cliente;

/**
 *
 * @author User
 */
@WebServlet("/vendas")
public class VendaController extends HttpServlet {
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao == null || acao.trim().isEmpty()){
            acao = "novo";
        }
        try {
            switch (acao) {
                case "listar":
                    listar (request, response);
                    break;
                case "excluir":
                    excluir (request, response);
                    break;
                case "novo":
                    novo (request, response);
                    break;
                default:
                    abrirFormulario (request, response);
            }
        }catch(RuntimeException ex){
            request.setAttribute("erro", ex.getMessage());
            abrirFormulario (request, response);

        }
                
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
