package server.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserList {
    public static UserList instance = null;
    private final List<String> onlineUsers = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public UserList() {

    }

    public static UserList getInstance() {
        if(instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public void addUserInList(Home home, String userIP) {
        lock.lock();
        try {
            onlineUsers.add(userIP);
            home.updateUserTable(onlineUsers);
        } finally {
            lock.unlock();
        }
    }

    public void removeUserInList(Home home, String userIP) {
        lock.lock();
        try {
            onlineUsers.remove(userIP);
            home.updateUserTable(onlineUsers);
        } finally {
            lock.unlock();
        }
    }
}
