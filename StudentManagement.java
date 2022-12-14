import java.util.*;

public class StudentManagement {
    private AVL tree;
    private Stack<Node> undoState;
    private Stack<Node> redoState;

    public StudentManagement() {
        this.tree = new AVL();
        this.undoState = new Stack<Node>();
        this.redoState = new Stack<Node>();
    }

    public AVL getTree() {
        return this.tree;
    }

    // Requirement 1
    public boolean addStudent(Student st) {
        if (tree.contain(st.getId())!=true){
            undoState.push(tree.root);
            redoState.removeAll(redoState);
            tree.insert(st);
            return true;
        }
        return false;
    }

    // Requirement 2
    public Student searchStudentById(int id) {
        if (tree.contain(id)==true){
            redoState.removeAll(redoState);
            Student student=tree.search(id).getData();
            return student;
        }
        return null;
    }

    // Requirement 3
    public boolean removeStudent(int id) {
        if (tree.contain(id)==true){
            undoState.push(tree.root);
            redoState.removeAll(redoState);
            Student student=tree.search(id).getData();
            tree.delete(student);
            return true;
        }
        return false;
    }

    // Requirement 4
    public void undo() {
        redoState.push(tree.root);
        if (!undoState.empty())
            tree.setRoot(undoState.pop());
    }

    // Requirement 5
    public void redo() {
        if(!redoState.empty())
            tree.setRoot(redoState.pop());;
    }

    // Requirement 6
    public ScoreAVL scoreTree(AVL tree) {
        // code here
        return null;
    }
}
