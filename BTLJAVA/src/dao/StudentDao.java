package dao;

import entity.Student;
import entity.StudentXML;
import utils.FileUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * StudentDao class
 *
 * @author
 */
public class StudentDao {
    private static final String STUDENT_FILE_NAME = "src/student.xml";


    private List<Student> listStudents;

    public StudentDao() {
        this.listStudents = readListStudents();
        if (listStudents == null) {
            listStudents = new ArrayList<Student>();
        }
    }

    /**
     * Lưu các đối tượng student vào file student.xml
     *
     * @param students
     */
    public void writeListStudents(List<Student> students) {
        StudentXML studentXML = new StudentXML();
        studentXML.setStudent(students);
        FileUtils.writeXMLtoFile(STUDENT_FILE_NAME, studentXML);
    }

    /**
     * Đọc các đối tượng student từ file student.xml
     *
     * @return list student
     */
    public List<Student> readListStudents() {

        List<Student> list = new ArrayList<Student>();
        StudentXML studentXML = (StudentXML) FileUtils.readXMLFile(
                STUDENT_FILE_NAME, StudentXML.class);
        if (studentXML != null) {
            list = studentXML.getStudent();
        }
        return list;
    }
    /**
     * Tìm kiếm theo 
     * @param name
     * @return
     */
    public List<Student> searchByName(String name) {
        List<Student> resultList = new ArrayList<Student>();
        for (Student student : listStudents) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                resultList.add(student);
            }
        }
        return resultList;
    }
    /**
     * Tìm kiếm sinh viên theo điểm trung bình tích luỹ (GPA)
     * @param diem GPA cần tìm kiếm
     * @return Danh sách sinh viên thỏa mãn điều kiện tìm kiếm
     */
    public List<Student> searchByGPA(double diem) {
        List<Student> resultList = new ArrayList<Student>();
        for (Student student : listStudents) {
            if (student.getDiem() == diem) {
                resultList.add(student);
            }
        }
        return resultList;
    }




    /**
     * thêm student vào listStudents và lưu listStudents vào file
     *
     * @param student
     */
    public void add(Student student) {
        int id = 1;
        if (listStudents != null && listStudents.size() > 0) {
// Lấy ID lớn nhất hiện có trong danh sách sinh viên
            int maxId = listStudents.stream().mapToInt(Student::getId).max().getAsInt();
// Tăng ID lên 1 đơn vị
            id = maxId + 1;
        }
        student.setId(id);
        listStudents.add(student);
        writeListStudents(listStudents);
    }

    /**
     * cập nhật student vào listStudents và lưu listStudents vào file
     *
     * @param student
     */
    public void edit(Student student) {
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {
                listStudents.get(i).setName(student.getName());
                listStudents.get(i).setDiem(student.getDiem());
                listStudents.get(i).setMon(student.getMon());
                listStudents.get(i).setKhoi(student.getKhoi());
                writeListStudents(listStudents);
                break;
            }
        }
    }

    /**
     * xóa student từ listStudents và lưu listStudents vào file
     *
     * @param student
     */
    public boolean delete(Student student) {
        boolean isFound = false;
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {
                student = listStudents.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listStudents.remove(student);
// Cập nhật lại ID của các sinh viên ở trên
            for (int i = 0; i < listStudents.size(); i++) {
                listStudents.get(i).setId(i + 1);
            }
            writeListStudents(listStudents);
            return true;
        }
        return false;
    }

    /**
     * sắp xếp danh sách student theo name theo tứ tự tăng dần
     */
    public void sortStudentByName() {
        Collections.sort(listStudents, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                return student1.getName().compareTo(student2.getName());
            }
        });
    }

    /**
     * sắp xếp danh sách student theo GPA theo tứ tự tăng dần
     */
    public void sortStudentByGPA() {
        Collections.sort(listStudents, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                if (student1.getDiem() > student2.getDiem()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
    public void sortStudentByID() {
        Collections.sort(listStudents, new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                if (student1.getId() > student2.getId()) {
                    return 1;
                }
                return -1;
            }
        });
    }


}
