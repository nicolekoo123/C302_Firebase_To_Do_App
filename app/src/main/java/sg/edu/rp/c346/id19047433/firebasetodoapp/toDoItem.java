package sg.edu.rp.c346.id19047433.firebasetodoapp;

public class toDoItem {
    private String title;
    private String date;

    @Override
    public String toString() {
        return "toDoItem{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
    public toDoItem() {
// Default constructor required for calls to snapshot.toObject(Message.class)
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public toDoItem(String date, String title){
        this.date = date;
        this.title = title;
    }
}
