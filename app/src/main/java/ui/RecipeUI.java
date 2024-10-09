package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    // RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示する。
    // 読み込んだレシピデータが空の場合は、 No recipes available. というメッセージを出力します。
    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");
                // 数字入力を促す
                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        searchRecipe();
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        // メソッドを呼び出してリストに追加
        ArrayList<String> recipes = fileHandler.readRecipes();
        // リストが空の場合の処理
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            // リストにデータがあるときの処理
        } else {
            System.out.println("Recipes:");
            System.out.println("-----------------------------------");

            for (String recipe : recipes) {
                // レシピのデータをカンマ区切りで2つに分割する
                String[] parts = recipe.split(",", 2);
                // 2つに区切れた場合
                if (parts.length == 2) {
                    String recipeName = parts[0]; // 料理名
                    String ingredients = parts[1];// 食材
                    // 出力処理
                    System.out.println("Recipe Name:" + recipeName);
                    System.out.println("Main Ingredients:" + ingredients);
                    System.out.println("-----------------------------------");
                }
            }
        }

    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */

    // ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
    private void addNewRecipe() throws IOException {
        // 入力を促す
        System.out.println("Enter recipe name:");
        String recipeName = reader.readLine();
        // 入力を促す
        System.out.println("Enter main ingredients (comma separated):");
        String ingredients = reader.readLine();
        // レシピと食材を追加する
        fileHandler.addRecipe(recipeName, ingredients);
        System.out.println("Recipe added successfully.");
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {
        //入力を促す
        System.out.println("Enter search query (e.g., 'name=Tomato&ingredient=Garlic'):");
        //入力されたものを小文字に変換させる
        String query = reader.readLine().toLowerCase();
        //リストにデータを読み込む
        ArrayList<String> recipes = fileHandler.readRecipes();
        //フラグ作成
        boolean found = false;

        System.out.println("Search Results:");

        for (String recipe : recipes) {
            //検索結果と一致した場合の処理
            if (recipe.toLowerCase().contains(query)) {
                String[] parts = recipe.split(",", 2);
                if (parts.length == 2) {
                    String recipeName = parts[0];
                    String ingredients = parts[1];

                    System.out.println("Recipe Name: " + recipeName);
                    System.out.println("Main Ingredients: " + ingredients);
                    System.out.println("-----------------------------------");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No matching recipes found.");
    }
}
}
