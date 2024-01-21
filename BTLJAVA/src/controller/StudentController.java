package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.StudentDao;
import entity.Student;
import view.StudentView;


public class StudentController {
    private StudentDao studentDao;
    private StudentView studentView;


    public StudentController(StudentView view) {
        this.studentView = view;
        studentDao = new StudentDao();
        view.addthongkeBtn(new addthongkeBtnListener());
        view.addAddStudentListener(new AddStudentListener());
        view.addEdiStudentListener(new EditStudentListener());
        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addClearListener(new ClearStudentListener());
            view.addSearchnameStudentListener(new SearchnameStudentListener());
        view.addSearchgpaStudentListener(new SearchgpaStudentListener());
        view.addSortStudentGPAListener(new SortStudentGPAListener());
        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
    }

    public void showStudentView() {
        List<Student> studentList = studentDao.getListStudents();
        studentView.setVisible(true);
        studentView.showListStudents(studentList);
    }
    class SearchnameStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String keyword = studentView.getSearchnameValue();
            List<Student> searchResult = studentDao.searchByName(keyword);
            studentView.showListStudents(searchResult);
        }
    }

    class SearchgpaStudentListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            double keyword = studentView.getSearchgpaValue();
            List<Student> searchResult = studentDao.searchByGPA(keyword);
            studentView.showListStudents(searchResult);
        }
    }



    /**
     * Lớp AddStudentListener
     * chứa cài đặt cho sự kiện click button "Add"
     *
     */
    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentDao.add(student);
                studentView.showStudent(student);
                studentView.showListStudents(studentDao.getListStudents());
                studentView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditStudentListener
     * chứa cài đặt cho sự kiện click button "Edit"
     *
     */
    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentDao.edit(student);
                studentView.showStudent(student);
                studentView.showListStudents(studentDao.getListStudents());
                studentView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteStudentListener
     * chứa cài đặt cho sự kiện click button "Delete"
     *
     */
    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentDao.delete(student);
                studentView.clearStudentInfo();
                studentView.showListStudents(studentDao.getListStudents());
                studentView.showMessage("Xóa thành công!");
            }
        }
    }


    /**
     * Lớp ClearStudentListener
     * chứa cài đặt cho sự kiện click button "Clear"
     *
     */
    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                studentDao.sortStudentByID();
                studentView.showListStudents(studentDao.getListStudents());
            studentView.clearStudentInfo();
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     *
     */
    class SortStudentGPAListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortStudentByGPA();
            studentView.showListStudents(studentDao.getListStudents());
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     *
     */
    class SortStudentNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortStudentByName();
            studentView.showListStudents(studentDao.getListStudents());
        }
    }

    /**
     * Lớp ListStudentSelectionListener
     * chứa cài đặt cho sự kiện chọn student trong bảng student
     *
     */
    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
    class addthongkeBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<Student> allStudents = studentDao.getListStudents();
            int[] ageCount = new int[100];
            float maxDiem = 0;
            for (Student student : allStudents) {
                float diem = student.getDiem();
                if (diem > maxDiem) {
                    maxDiem = diem;
                }
                ageCount[(int) diem]++;
            }
            StringBuilder result = new StringBuilder();
            result.append("Thống kê điểm sinh viên :\n");
            for (int i = 0; i <= maxDiem; i++) {
                if (ageCount[i] > 0) {
                    result.append("Số học sinh  ").append(i).append(" Điểm là : ").append(ageCount[i]).append("\n");
                }
            }
            JOptionPane.showMessageDialog(studentView, result.toString());
        }
    }


}

