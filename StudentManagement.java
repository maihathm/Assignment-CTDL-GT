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
            Node tempRoot=tree.cloneTree(tree.getRoot());
            undoState.push(tempRoot);
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
            Node tempRoot=tree.cloneTree(tree.getRoot());
            undoState.push(tempRoot);
            redoState.removeAll(redoState);
            Student student=tree.search(id).getData();
            tree.delete(student);
            return true;
        }
        return false;
    }

    // Requirement 4
    public void undo() {
        Node tempRoot=tree.cloneTree(tree.getRoot());
        redoState.push(tempRoot);
        if (!undoState.empty())
            tree.setRoot(undoState.pop());
    }

    // Requirement 5
    public void redo() {
        Node tempRoot=tree.cloneTree(tree.getRoot());
        undoState.push(tempRoot);
        if(!redoState.isEmpty())
            tree.setRoot(redoState.pop());;
    }

    // Requirement 6
    public ScoreAVL scoreTree(AVL avlTree) {
        ScoreAVL scoreTree=new ScoreAVL();
        Node root=tree.getRoot();
        Queue<Node> tmp = new LinkedList<Node>();
        tmp.add(root);
        while (!tmp.isEmpty()) {
            Node temp = tmp.poll();
            scoreTree.insert(temp.getData());
            if (temp.getLeft() != null) {
                tmp.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                tmp.add(temp.getRight());
            }
        }
        return scoreTree;
    }
}
