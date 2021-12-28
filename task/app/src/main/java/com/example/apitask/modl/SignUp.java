
package com.example.apitask.modl;

import java.util.List;

public class SignUp {


    /**
     * msg : Login is successfull.
     * status : true
     * result : [{"id":2,"first_name":"Maged","last_name":"Maher","full_name":"Maged Maher","email":"dev.magedmaher@gmail.com","phone":"01159848839","image":"http://link.to/profile-picture.jpg"}]
     */

    private String msg;
    private boolean status;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 2
         * first_name : Maged
         * last_name : Maher
         * full_name : Maged Maher
         * email : dev.magedmaher@gmail.com
         * phone : 01159848839
         * image : http://link.to/profile-picture.jpg
         */

        private int id;
        private String first_name;
        private String last_name;
        private String full_name;
        private String email;
        private String phone;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
