class BST {
  class Node {
      int key;
      Node left, right;

      public Node(int item) {
          key = item;
          left = right = null;
      }
  }

  Node root;

  BST() {
      root = null;
  }

  void insert(int key) {
      root = insertRec(root, key);
  }

  Node insertRec(Node root, int key) {
      if (root == null) {
          root = new Node(key);
          return root;
      }
      if (key < root.key)
          root.left = insertRec(root.left, key);
      else if (key > root.key)
          root.right = insertRec(root.right, key);
      return root;
  }

  void inorder() {
      inorderRec(root);
      System.out.println();
  }

  void inorderRec(Node root) {
      if (root != null) {
          inorderRec(root.left);
          System.out.print(root.key + " ");
          inorderRec(root.right);
      }
  }

  void preorder() {
      preorderRec(root);
      System.out.println();
  }

  void preorderRec(Node root) {
      if (root != null) {
          System.out.print(root.key + " ");
          preorderRec(root.left);
          preorderRec(root.right);
      }
  }

  void postorder() {
      postorderRec(root);
      System.out.println();
  }

  void postorderRec(Node root) {
      if (root != null) {
          postorderRec(root.left);
          postorderRec(root.right);
          System.out.print(root.key + " ");
      }
  }

  Node deleteKey(int key) {
      root = deleteRec(root, key);
      return root;
  }

  Node deleteRec(Node root, int key) {
      if (root == null) return root;
      if (key < root.key)
          root.left = deleteRec(root.left, key);
      else if (key > root.key)
          root.right = deleteRec(root.right, key);
      else {
          if (root.left == null)
              return root.right;
          else if (root.right == null)
              return root.left;
          root.key = minValue(root.right);
          root.right = deleteRec(root.right, root.key);
      }
      return root;
  }

  int minValue(Node root) {
      int minv = root.key;
      while (root.left != null) {
          minv = root.left.key;
          root = root.left;
      }
      return minv;
  }

  boolean search(int key) {
      return searchRec(root, key) != null;
  }

  Node searchRec(Node root, int key) {
      if (root == null || root.key == key)
          return root;
      if (key < root.key)
          return searchRec(root.left, key);
      return searchRec(root.right, key);
  }

  void path(int key) {
      pathRec(root, key);
      System.out.println();
  }

  void pathRec(Node root, int key) {
      if (root == null) return;
      System.out.print(root.key + " ");
      if (key < root.key)
          pathRec(root.left, key);
      else if (key > root.key)
          pathRec(root.right, key);
  }
}

class Main {
  public static void main(String[] args) {

    BST lab5Tree = new BST();
        int[] values = {13, 22, 36, 5, 48, 17, 39, 2, 26, 40, 29, 34, 10};
        for (int value : values) {
            lab5Tree.insert(value);
        }

        lab5Tree.deleteKey(17);

        System.out.print("Inorder: ");
        lab5Tree.inorder();

        System.out.print("Postorder: ");
        lab5Tree.postorder();

        System.out.print("Preorder: ");
        lab5Tree.preorder();

        System.out.println("Search 36: " + lab5Tree.search(36));
        System.out.println("Search 37: " + lab5Tree.search(37));

        System.out.print("Path to 2: ");
        lab5Tree.path(2);

        System.out.print("Path to 34: ");
        lab5Tree.path(34);

  }
}