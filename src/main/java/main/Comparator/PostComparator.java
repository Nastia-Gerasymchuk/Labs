package main.Comparator;


import main.model.Post;

import java.util.Comparator;

public class PostComparator implements Comparator<Post>{
    @Override
    public int compare(Post o1, Post o2) {
        return Double.compare(o1.getSalary(),o2.getSalary());
    }
}
