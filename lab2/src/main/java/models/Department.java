package models;

public class Department extends Model {
    //TODO IMPLEMENT PARENT ID
    private String title;
    private String city;

    public Department() {
    }

    public Department(long id) {
        super(id);
    }

    public Department(String title, String city) {
        this.title = title;
        this.city = city;
    }

    public Department(long id, String title, String city) {
        super(id);
        this.title = title;
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        if (!super.equals(o)) return false;

        Department that = (Department) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return city != null ? city.equals(that.city) : that.city == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department {" +
                "\n\tID = '" + getId() + '\'' +
                ", \n\ttitle = '" + title + '\'' +
                ", \n\tcity = '" + city + '\'' +
                "\n}";
    }
}
