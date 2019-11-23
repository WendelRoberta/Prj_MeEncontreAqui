package br.com.meencontreaqui.prj_meencontreaqui;

import java.io.Serializable;

class User implements Serializable {
        private long id;
        private String name;
        private String password;
        private long longitude;
        private long latitude;
        private int active;


        public User() {
        }

        public User(String name,String password) {
            super();
            this.name = name;
            this.password = password;
            this.longitude = 0;
            this.latitude = 0;
            this.active = 0;
        }

    public User(String name, String password, Long longitude, Long latitude, int active) {
        this.name = name;
        this.password = password;
        this.longitude = longitude;
        this.latitude = latitude;
        this.active = active;
    }

    @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((password == null) ? 0 : password.hashCode());
            return result;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            User other = (User) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (password == null) {
                if (other.password != null)
                    return false;
            } else if (!password.equals(other.password))
                return false;
            return true;
        }


        public long getId() {
            return id;
        }


        public void setId(long id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getPassword() {
            return password;
        }


        public void setPassword(String password) {
            this.password = password;
        }


        public long getLongitude() {
            return longitude;
        }


        public void setLongitude(long longitude) {
            this.longitude = longitude;
        }


        public long getLatitude() {
            return latitude;
        }


        public void setLatitude(long latitude) {
            this.latitude = latitude;
        }


        public int getActive() {
            return active;
        }


        public void setActive(int active) {
            this.active = active;
        }




        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + ", password=" + password + ", longitude=" + longitude
                    + ", latitude=" + latitude + ", active=" + active + "]";
        }
    }

