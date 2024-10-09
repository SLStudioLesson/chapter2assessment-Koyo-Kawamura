package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;
    //ファイルパスの設定
    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }
    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br> 
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */

     //recipes.txtからレシピデータを読み込み、それをリスト形式で返す。
    public ArrayList<String> readRecipes() {
        //リストの作成
        ArrayList<String> recipes = new ArrayList<>();
        //ファイルの読み込み
         try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //1行ずつnullになるまで読み込み
            while((line = br.readLine()) != null){
                //リストに追加する
                recipes.add(line);
            }
            //IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
         } catch (IOException e) {
             System.out.println("Error reading file:" + e.getMessage());
        }
        return recipes;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     // 

     //新しいレシピをrecipes.txtに追加します。
     //レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
    public void addRecipe(String recipeName, String ingredients) {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))){
            bw.write(recipeName + "," + ingredients);
            //改行
            bw.newLine();
            //IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
         } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
         }
    }
}
