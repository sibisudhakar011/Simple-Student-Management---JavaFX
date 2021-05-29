package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Student;
import tools.DB_Con;
import tools.Utils;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Home implements Initializable {

    Utils utils = new Utils();
    DB_Con con = new DB_Con();

    List<Pane> clicked = new ArrayList<Pane>();
    List<AnchorPane> pages = new ArrayList<AnchorPane>();

    @FXML AnchorPane home_pane;
    @FXML AnchorPane view_record;
    @FXML AnchorPane add_student;
    @FXML AnchorPane update_student;
    @FXML AnchorPane remove_student;

    @FXML TextField student_id_search_update;
    @FXML TextField student_id_search_remove;

    /*CUD*/
    @FXML TextField first_name_add;
    @FXML TextField first_name_add1;
    @FXML TextField first_name_add11;
    @FXML TextField middle_name_add;
    @FXML TextField middle_name_add1;
    @FXML TextField middle_name_add11;
    @FXML TextField last_name_add;
    @FXML TextField last_name_add1;
    @FXML TextField last_name_add11;
    @FXML TextField mobile_number_add;
    @FXML TextField mobile_number_add1;
    @FXML TextField mobile_number_add11;
    @FXML TextField email_address_add;
    @FXML TextField email_address_add1;
    @FXML TextField email_address_add11;
    @FXML DatePicker date_of_birth_add;
    @FXML DatePicker date_of_birth_add1;
    @FXML DatePicker date_of_birth_add11;
    @FXML ComboBox gender_add;
    @FXML ComboBox gender_add1;
    @FXML ComboBox gender_add11;
    @FXML TextField current_address_add;
    @FXML TextField current_address_add1;
    @FXML TextField current_address_add11;
    @FXML ComboBox department_add;
    @FXML ComboBox department_add1;
    @FXML ComboBox department_add11;
    @FXML ComboBox stud_type_add;
    @FXML ComboBox stud_type_add1;
    @FXML ComboBox stud_type_add11;
    @FXML ComboBox year_add;
    @FXML ComboBox year_add1;
    @FXML ComboBox year_add11;
    @FXML ComboBox section_add;
    @FXML ComboBox section_add1;
    @FXML ComboBox section_add11;
    @FXML ComboBox program_add;
    @FXML ComboBox program_add1;
    @FXML ComboBox program_add11;


    @FXML Text txt_total_students;
    @FXML Text txt_new_students;
    @FXML Text txt_removed_student;
    @FXML Text txt_regular_student;
    @FXML Text txt_irregular_student;
    @FXML Text time_display;
    @FXML ComboBox filter;
    @FXML TextField view_searchfield;
    @FXML Text Greet;

    @FXML Pane home_btn;
    @FXML Pane view_record_btn;
    @FXML Pane add_new_student_btn;
    @FXML Pane update_student_btn;
    @FXML Pane remove_student_btn;

    @FXML TableView table_view;
    @FXML TableColumn col_id;
    @FXML TableColumn col_fullname;
    @FXML TableColumn col_email;
    @FXML TableColumn col_yearlevel;
    @FXML TableColumn col_program;
    @FXML TableColumn col_department;
    @FXML TableColumn col_gender;

    Student selected;

    /*Events*/
    @FXML public void searchStudent(ActionEvent e){
        try{
            if(view_searchfield.getText().length()==0){
                reqDefTableData();
                return;
            }
            PreparedStatement ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS WHERE "+filter.getValue().toString()+" like '%"+view_searchfield.getText()+"%' AND isActive=1");
            loadTableData(con.query(ps));
        }catch (Exception ex){}
    }

    public void setSelectedStudent(){try {
        selected = ((Student) table_view.getSelectionModel().getSelectedItem());
    }catch (Exception e){}}
    public void setUpdateStudentForm(){
        first_name_add1.setText(selected.getFname());
        middle_name_add1.setText(selected.getMname());
        last_name_add1.setText(selected.getLname());
        mobile_number_add1.setText(selected.getMobile_number());
        email_address_add1.setText(selected.getEmail());
        LocalDate ld = LocalDate.parse(selected.getDateofbirth()+"", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        date_of_birth_add1.setValue(ld);
        gender_add1.setValue(selected.getGender());
        department_add1.setValue(selected.getDepartment());
        current_address_add1.setText(selected.getAddress());
        stud_type_add1.setValue(selected.getStudent_type());
        year_add1.setValue(selected.getYear_level()+"");
        section_add1.setValue(selected.getSection());
        program_add1.setValue(selected.getProgram());
    }
    public void setRemoveStudentForm(){
        first_name_add11.setText(selected.getFname());
        middle_name_add11.setText(selected.getMname());
        last_name_add11.setText(selected.getLname());
        mobile_number_add11.setText(selected.getMobile_number());
        email_address_add11.setText(selected.getEmail());
        LocalDate ld = LocalDate.parse(selected.getDateofbirth()+"", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        date_of_birth_add11.setValue(ld);
        gender_add11.setValue(selected.getGender());
        department_add11.setValue(selected.getDepartment());
        current_address_add11.setText(selected.getAddress());
        stud_type_add11.setValue(selected.getStudent_type());
        year_add11.setValue(selected.getYear_level()+"");
        section_add11.setValue(selected.getSection());
        program_add11.setValue(selected.getProgram());
    }

    @FXML public void Update_Selected(ActionEvent e){
        setSelectedStudent();
        if(selected == null){
            Utils.alert("Warning","No Selected Student","Please Select A Student", Alert.AlertType.WARNING);
            return;
        }
        setUpdateStudentForm();
        hideAllPages();
        deselectAllBtn();
        update_student_btn.getStyleClass().add("selected_nav_btn");
        setVis(update_student);
    }
    @FXML public void Remove_Selected(ActionEvent e){
        setSelectedStudent();
        if(selected == null){
            Utils.alert("Warning","No Selected Student","Please Select A Student", Alert.AlertType.WARNING);
            return;
        }
        hideAllPages();
        deselectAllBtn();
        remove_student_btn.getStyleClass().add("selected_nav_btn");
        setVis(remove_student);
        setRemoveStudentForm();
    }

    @FXML public void Update_Student(ActionEvent e){
        try{
            if(!Utils.isValidMobileNumber(mobile_number_add.getText())){
                Utils.alert("Invalid Input","Please Check Input","Mobile Number exceed 11 digits or contains non numeric character!", Alert.AlertType.WARNING);
                return;
            }
            PreparedStatement ps = DB_Con.getCon().prepareStatement(
                    "UPDATE STUDENTS set fname=?,mname=?,lname=?,mobile_number=?,email=?,dateofbirth=?,gender=?,student_type=?,address=?,department=?,year_level=?,program=?,section=? where id = ?");
            ps.setString(1,first_name_add1.getText());
            ps.setString(2,middle_name_add1.getText());
            ps.setString(3,last_name_add1.getText());
            ps.setString(4,mobile_number_add1.getText());
            ps.setString(5,email_address_add1.getText());
            LocalDate ld = date_of_birth_add1.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            ps.setString(6,formatter.format(ld));
            ps.setString(7, gender_add1.getValue().toString());
            ps.setString(8,stud_type_add1.getValue().toString());
            ps.setString(9,current_address_add1.getText());
            ps.setString(10,department_add1.getValue().toString());
            ps.setInt(11,Integer.parseInt(year_add1.getValue().toString()));
            ps.setString(12, program_add1.getValue().toString());
            ps.setString(13,section_add1.getValue().toString());
            ps.setInt(14,selected.getId());
            con.update(ps);
            clearForms();
            Utils.log("[Student Updated] -> "+selected.getId());
            selected = null;
            reqDefTableData();
            Utils.alert("Notification","Successfuly Updated","Student Information Updated Successfully",Alert.AlertType.INFORMATION);
        }catch (Exception E){
            Utils.alert("Warning","Missing or Invalid Informations","Please Fill Upp all fields", Alert.AlertType.WARNING);
            E.printStackTrace();
        }
    }

    @FXML public void Search_Remove_Student(ActionEvent e){
        clearForms();
        try{
            PreparedStatement ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS WHERE id=? AND isActive=1");
            ps.setString(1,student_id_search_remove.getText());
            int size = con.query(ps);
            selected = studs(con.getResult(),size)[0];

            if(selected == null || student_id_search_remove.getText().length() == 0){
                Utils.alert("Warning","","Can't Find Student With That ID!", Alert.AlertType.WARNING);
                return;
            }
            setRemoveStudentForm();
        }catch (Exception E){
            Utils.alert("Warning","","Unable To Find That Student", Alert.AlertType.ERROR);
            E.printStackTrace();
        }
    }
    @FXML public void Clear_Remove_Form(ActionEvent e){
        clearForms();
        student_id_search_remove.setText("");
        selected = null;
    }
    @FXML public void Clear_Update_Form(ActionEvent e){
        clearForms();
        student_id_search_update.setText("");
        selected = null;
    }

    @FXML public void Add_Student(ActionEvent e){
        try{
            if(!Utils.isValidMobileNumber(mobile_number_add.getText())){
                Utils.alert("Invalid Input","Please Check Input","Mobile Number exceed 11 digits or contains non numeric character!", Alert.AlertType.WARNING);
                return;
            }
            PreparedStatement ps = DB_Con.getCon().prepareStatement(
                    "Insert Into STUDENTS(fname,mname,lname,mobile_number,email,dateofbirth,gender,student_type,address,department,year_level,program,added_by,date_added,section)values" +
                            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,first_name_add.getText());
            ps.setString(2,middle_name_add.getText());
            ps.setString(3,last_name_add.getText());
            ps.setString(4,mobile_number_add.getText());
            ps.setString(5,email_address_add.getText());
            LocalDate ld = date_of_birth_add.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            ps.setString(6,formatter.format(ld));
            ps.setString(7, gender_add.getValue().toString());
            ps.setString(8,stud_type_add.getValue().toString());
            ps.setString(9,current_address_add.getText());
            ps.setString(10,department_add.getValue().toString());
            ps.setInt(11,Integer.parseInt(year_add.getValue().toString()));
            ps.setString(12, program_add.getValue().toString());
            ps.setString(13, Utils.user);
            ps.setString(14,utils.getCurDate());
            ps.setString(15,section_add.getValue().toString());
            con.update(ps);
            clearForms();
            Utils.alert("Notification","Successfully Added","Added New Student",Alert.AlertType.INFORMATION);
        }catch (Exception E){
            Utils.alert("Warning","Missing or Invalid Informations","Please Fill Upp all fields", Alert.AlertType.WARNING);
            Utils.alert("err","ERROR",E.toString(), Alert.AlertType.ERROR);
            E.printStackTrace();
        }
        reqDefTableData();
    }
    @FXML public void Search_Update_Student(ActionEvent e){
        clearForms();
        try{
            PreparedStatement ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS WHERE id=? AND isActive=1");
            ps.setString(1,student_id_search_update.getText());
            int size = con.query(ps);
            selected = studs(con.getResult(),size)[0];
            if(selected == null || student_id_search_update.getText().length() == 0){
                Utils.alert("Warning","","Can't Find Student With That ID!", Alert.AlertType.WARNING);
                return;
            }
            setUpdateStudentForm();
        }catch (Exception E){
            Utils.alert("","","Unable To Find That Student", Alert.AlertType.ERROR);
            E.printStackTrace();
        }
    }
    @FXML public void Clear_Add_New_Form(ActionEvent e){ clearForms(); }
    @FXML public void Remove_Student(ActionEvent e){
        try{
            if(selected == null){
                Utils.alert("","No Selected Student", "Please Select A Student", Alert.AlertType.WARNING);
                return;
            }
            Alert res = Utils.alert("Confirm Deletion","Delete Student","Are you sure to delete student with id of "+selected.getId(), Alert.AlertType.CONFIRMATION);
            if(res.getResult() != ButtonType.OK) return;
            PreparedStatement ps = DB_Con.getCon().prepareStatement("UPDATE STUDENTS set isActive=0 where id=?");
            ps.setInt(1,selected.getId());
            con.update(ps);
            clearForms();
            reqDefTableData();
            Utils.alert("Action Done","Student Successfuly Deleted",String.format("Student [ %d ] %s was deleted",selected.getId(),selected.getFullname()), Alert.AlertType.INFORMATION);
            selected = null;
            student_id_search_remove.setText("");
        }catch (Exception ex){}
    }

    /*events end*/

    public Student [] studs(ResultSet res, int querySize){
        Student t [] = new Student[querySize];
        int ptr = 0;
        try{
            while(res.next()){
                int id = res.getInt("id"), year_lvl = res.getInt("year_level");
                String fullname = String.format("%s %s %s",res.getString("fname"),res.getString("mname").charAt(0),res.getString("lname")),
                        email = res.getString("email"), program = res.getString("program"), department = res.getString("department"),
                        gender = res.getString("gender"), mobile = res.getString("mobile_number"), student_type = res.getString("student_type"),
                        address = res.getString("address");
                Date date_of_birth = res.getDate("dateofbirth");
                t[ptr++] = (new Student(res.getString("section"),fullname,res.getString("fname"),res.getString("mname"),res.getString("lname"), email, program, department, gender, mobile, student_type, address, date_of_birth, id, year_lvl));
            }

        }catch (Exception e){ }
        return t;
    }

    public void loadTableData(int size){
        ObservableList<Student> stud = FXCollections.observableArrayList();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_yearlevel.setCellValueFactory(new PropertyValueFactory<>("year_level"));
        col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_program.setCellValueFactory(new PropertyValueFactory<>("program"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        for(Student T : studs(con.getResult(), size)) stud.add(T);

        table_view.setItems(stud);
    }

    public void reqDefTableData(){
        try {
            PreparedStatement ps = DB_Con.getCon().prepareStatement("SELECT * from STUDENTS where isActive=1");
            loadTableData(con.query(ps));
        }catch (Exception e){}
    }
    public void initCombos(ComboBox com, String key){
        try{
            PreparedStatement ps = DB_Con.getCon().prepareStatement("Select val from APP_OPTIONS where keyword = ?");
            ps.setString(1,key);
            con.query(ps);
            ResultSet res = con.getResult();
            ObservableList <String> filters = FXCollections.observableArrayList();

            while(res.next()) filters.add(res.getString("val"));

            com.setItems(filters);
            com.setValue(filters.get(0));

        }catch (Exception e){}
    }

    public Home(){ Utils.log("[Home Win] Started");}

    public void deselectAllBtn(){ for(var b : clicked)  b.getStyleClass().remove("selected_nav_btn"); }
    public void hideAllPages(){ for(var T : pages) T.setVisible(false); }
    public void clearForms(){
        Utils.clearTextFields(new TextField[]{ first_name_add, middle_name_add, last_name_add, mobile_number_add, email_address_add, current_address_add,  first_name_add1, middle_name_add1, last_name_add1, mobile_number_add1, email_address_add1, current_address_add1, first_name_add11, middle_name_add11, last_name_add11, mobile_number_add11, email_address_add11, current_address_add11 });
        Utils.clearCombos(new ComboBox[]{ gender_add, stud_type_add, program_add, section_add, year_add, gender_add1, stud_type_add1, program_add1, section_add1, year_add1, gender_add11, stud_type_add11, program_add11, section_add11, year_add11 });
    }
    void setVis(AnchorPane p) { p.setVisible(true); }

    public void checkPane(Pane src){
        if(src == home_btn) setVis(home_pane);
        else if(src == view_record_btn) setVis(view_record);
        else if(src == add_new_student_btn) setVis(add_student);
        else if(src == update_student_btn) setVis(update_student);
        else if(src == remove_student_btn) setVis(remove_student);
    }

    @FXML
    public void PANE_BTN(MouseEvent e){
        var curPane = (Pane) e.getSource();
        hideAllPages();
        checkPane(curPane);
        deselectAllBtn();
        curPane.getStyleClass().add("selected_nav_btn");
    }

    @FXML public void EXIT_APP(MouseEvent e){ Utils.quitApp(); }
    @FXML public void LOGOUT(MouseEvent e){
        try {
            Node node = (Node) e.getSource();
            Stage appStage = (Stage) node.getScene().getWindow();
            FXMLLoader load = new FXMLLoader(getClass().getResource("../UI/Login.fxml"));
            Parent HOMEE = load.load();
            Scene appScene = new Scene(HOMEE);
            appStage.setScene(appScene);
            appStage.setTitle("Mini Student Management - Auth");
            appStage.centerOnScreen();
            appStage.setMinHeight(562);
            appStage.setMinWidth(1102);
            appStage.setResizable(true);
        }catch (Exception ex){}
    }
    @FXML public void ABOUT(MouseEvent e){
        try{
            Node node = (Node) e.getSource();
            Stage appStage = (Stage) node.getScene().getWindow();

            appStage.setMinWidth(610.0);
            appStage.setMinHeight(348.0);
            FXMLLoader load = new FXMLLoader(getClass().getResource("../UI/About.fxml"));
            Parent HOMEE = load.load();
            Scene appScene = new Scene(HOMEE);
            appStage.setScene(appScene);
            appStage.setTitle("Mini Student Management - About");
            appStage.centerOnScreen();
        }catch (Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        utils.setabcdef(new Text[]{txt_total_students,txt_new_students,txt_removed_student,txt_regular_student,txt_irregular_student});
        utils.setDisplayed_date(time_display);
        utils.startAutoUpdateabcdef();

        clicked.add(home_btn);
        clicked.add(view_record_btn);
        clicked.add(add_new_student_btn);
        clicked.add(update_student_btn);
        clicked.add(remove_student_btn);

        pages.add(home_pane);
        pages.add(view_record);
        pages.add(add_student);
        pages.add(update_student);
        pages.add(remove_student);

        home_pane.setVisible(true);

        reqDefTableData();
        initCombos(filter,"filter");
        initCombos(gender_add,"gender");
        initCombos(stud_type_add,"student_type");
        initCombos(program_add,"program");
        initCombos(department_add,"department");
        initCombos(year_add,"year");
        initCombos(section_add,"section");

        initCombos(gender_add1,"gender");
        initCombos(stud_type_add1,"student_type");
        initCombos(program_add1,"program");
        initCombos(department_add1 ,"department");
        initCombos(year_add1 ,"year");
        initCombos(section_add1 ,"section");

        initCombos(gender_add11,"gender");
        initCombos(stud_type_add11,"student_type");
        initCombos(program_add11,"program");
        initCombos(department_add11 ,"department");
        initCombos(year_add11 ,"year");
        initCombos(section_add11 ,"section");

        LocalDate ld = LocalDate.parse("2000-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        date_of_birth_add11.setValue(ld);
        date_of_birth_add.setValue(ld);
        date_of_birth_add1.setValue(ld);
    }
}
