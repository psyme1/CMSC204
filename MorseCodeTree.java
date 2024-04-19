package com.example.project5;
import javafx.util.Pair;

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
    private TreeNode<String> root;
    public MorseCodeTree(){
        root = new TreeNode<>("");
        buildTree();
    }
    public TreeNode<String> getRoot(){return root;}

    @Override
    public void insert(String code, String result) {
        addNode(root, code, result);
    }

    @Override
    public void addNode(TreeNode<String> root, String code, String letter) {
        if(code == null) return;
        if(code.equalsIgnoreCase("")){
            if(root.equals(this.root)){
                TreeNode<String> r = new TreeNode<>(letter);
                this.root = r;
                return;
            }
            TreeNode<String> parent = findParent(this.root, root.getData()).getKey();

            TreeNode<String> r = new TreeNode<>(letter);
            r.right = root.right;
            r.left = root.left;
            if(parent.left.equals(root)){
                parent.left = r;
            }else{
                parent.right = r;
            }


            return;
        }
        char l = code.charAt(0);
        if(code.length() > 1) {
            code = code.substring(1);
        }else{
            if(l == '.'){
                root.left = new TreeNode<>(letter);
            }else{
                if(l == '-'){
                    root.right = new TreeNode<>(letter);
                }
            }
            return;
        }
        if(l == '.'){
            if(root.left == null) return;
            addNode(root.left, code, letter);
        }
        if(l == '-'){
            if(root.right == null) return;
            addNode(root.right, code, letter);
        }
    }

    @Override
    public String fetch(String code) {
        if(root == null){
            buildTree();
        }
        return fetchNode(root, code);
    }

    @Override
    public String fetchNode(TreeNode<String> root, String code) {
        if(root == null || code== null || code.equalsIgnoreCase("")) if(root != null){return root.getData();} else{return "";};
        char c = code.charAt(0);
        if(code.length() > 1){code = code.substring(1);} else{if(c == '.'){
            if(root.left == null) return "";
            return root.left.getData();
        } if(c == '-'){ if(root.right == null) return ""; return root.right.getData();}}
        if(c == '.') return fetchNode(root.left, code); if(c == '-'){return fetchNode(root.right, code);}
        return "";
    }

    @Override
    public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRoot(TreeNode<String> newNode) {
        this.root = newNode;
    }

    @Override
    public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void buildTree() {
        insert("", "");
        insert(".", "e");
        insert("-", "t");
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        LNRoutputTraversal(root, list);
        return list;
    }

    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        if(root == null) return;
        LNRoutputTraversal(root.left, list);
        list.add(root.getData());
        LNRoutputTraversal(root.right, list);
    }

    private Pair<TreeNode<String>, Boolean> findParent(TreeNode<String> root, String data){
        if(root.left != null){Pair<TreeNode<String>, Boolean> p = findParent(root, data); if(p != null){ if(!p.getValue()) return new Pair<>(root, true); return p;}}
        if(data.equals(root.getData())) return new Pair<>(root, false);
        if(root.right != null){
            Pair<TreeNode<String>, Boolean> p = findParent(root, data);
            if(p != null){
                if(!p.getValue()){
                    return new Pair<>(root, true);
                }
                return p;
            }
        }
        return null;
    }

}
