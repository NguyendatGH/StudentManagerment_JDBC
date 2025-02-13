/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import bussiness.Student;
import data.StudentDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rio
 */
public class StudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "/StudentList.jsp";

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        if (action.equals("list")) {
            List<Student> students = StudentDAO.getStudents();
            request.setAttribute("students", students);
            url = "/StudentList.jsp";
        } else if (action.equals("addnew")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            if (gender == null) {
                gender = "";
            }
            String dateString = request.getParameter("DOB");

            String errString = dataValidation(name, gender, dateString);
            if (!errString.isEmpty()) {
                url = "/CreateStudent.jsp";
                request.setAttribute("errString", errString);
            } else {
                try {

                    Student student = new Student(id, name, gender, dateString);
                    StudentDAO.addStudent(student);

                    List<Student> students = StudentDAO.getStudents();
                    request.setAttribute("students", students);

                } catch (NumberFormatException e) {
                    request.setAttribute("errString", "Invalid price format");
                }
            }
        } else if (action.equals("delete")) {
            String id = request.getParameter("id");
            StudentDAO.deleteStudent(Integer.parseInt(id));

            List<Student> students = StudentDAO.getStudents();

            request.setAttribute("students", students);

        } else if (action.equals("edit")) {
            String id = request.getParameter("id");

            Student student = StudentDAO.getStudent(Integer.parseInt(id));
            student.setId(Integer.parseInt(id));

            url = "/EditStudent.jsp";

            request.setAttribute("student", student);
        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("idP"));

            Student studentF = StudentDAO.getStudent(id);

            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String DOB = request.getParameter("DOB");
            if (gender == null || gender.trim().isEmpty()) {
                gender = studentF.getGender();
            }
            String errStr = dataValidation(name, gender, DOB);

            if (!errStr.isEmpty()) {
                url = "/EditStudent.jsp";
                request.setAttribute("student", studentF);
                request.setAttribute("errStr", errStr);
                getServletContext().getRequestDispatcher(url).forward(request, response);
                return;
            }
            try {

                Student student = new Student(id, name, gender, DOB);

                StudentDAO.updateStudent(student);
                List<Student> students = StudentDAO.getStudents();
                request.setAttribute("students", students);

            } catch (NumberFormatException e) {
                request.setAttribute("errStr", "Invalid price format");
                url = "/EditStudent.jsp";
            }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private String dataValidation(String name, String gender, String DOB) {
        String errorString = "";
        if (name.isEmpty() || gender.isEmpty() || DOB.isEmpty()) {
            return "Data required in all field";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date validDate = sdf.parse(DOB);

        } catch (Exception e) {
            System.out.println(e);
        }
        return errorString;
    }
}
