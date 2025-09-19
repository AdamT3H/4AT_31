package org.example.task6;

import org.hibernate.Session;

public class Main {

    public static void createMeeting(String title, String date, String description, Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Meeting meeting = new Meeting(title, date, description, id);
        session.save(meeting);

        session.getTransaction().commit();
        session.close();

        System.out.println("Meeting created with ID: " + meeting.getId());
    }

    public static Meeting readMeeting(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Meeting meeting = session.get(Meeting.class, id);

        session.getTransaction().commit();
        session.close();

        if (meeting != null) {
            System.out.println("Meeting found: " + meeting.getTitle());
        } else {
            System.out.println("Meeting with ID " + id + " not found");
        }

        return meeting;
    }

    public static void updateMeeting(int id, String newTitle, String newDescription) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Meeting meeting = session.get(Meeting.class, id);
        if (meeting != null) {
            meeting.setTitle(newTitle);
            meeting.setDescription(newDescription);
            session.update(meeting);
            System.out.println("Meeting updated!");
        } else {
            System.out.println("Meeting with ID " + id + " not found");
        }

        session.getTransaction().commit();
        session.close();
    }

    public static void deleteMeeting(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Meeting meeting = session.get(Meeting.class, id);
        if (meeting != null) {
            session.delete(meeting);
            System.out.println("Meeting deleted!");
        } else {
            System.out.println("Meeting with ID " + id + " not found");
        }

        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        createMeeting("Project Start", "2025-09-19", "Kickoff meeting", 3);

        readMeeting(3);

        updateMeeting(3, "Updated Title", "Updated Description");

        deleteMeeting(3);
    }
}
