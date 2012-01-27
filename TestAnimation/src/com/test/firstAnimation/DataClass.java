//package com.test.firstAnimation;
//
//import android.app.Activity;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//import android.widget.TextView;
//
//
//public class DataClass extends Activity { //get rid of activity
// 
//	private String name;
//    private int score;
//    private int id;
//	SQLiteDatabase myDB= null;
//	String TableName = "HIGHSCORES";
//	String Data="";
//   
//   
//   public void createDB(){
//  /* Create a Database. */
//  
//   myDB = this.openOrCreateDatabase("HIT_GREG_DB", MODE_PRIVATE, null);
// 
//   /* Create a Table in the Database. */
//   myDB.execSQL("CREATE TABLE IF NOT EXISTS " + TableName + " (NAME VARCHAR, SCORE INT(3), ID INT(3));");
//   }
//   
//   public void writeToDB(){
//   /* Insert data to a Table*/
//   myDB.execSQL("INSERT INTO " + TableName + " (NAME, SCORE, ID)" + " VALUES (" + name + ", " + score + ", " + id + ");");
//   }
//   
//   public TextView getAllFromDB{
//   /*retrieve data from database */
//   Cursor c = myDB.rawQuery("SELECT * FROM " + TableName , null);
// 
//   int Column1 = c.getColumnIndex("name");
//   int Column2 = c.getColumnIndex("score");
// 
//   // Check if our result was valid.
//   c.moveToFirst();
//   if (c != null) {
//    // Loop through all Results
//    do {
//     String Name = c.getString(Column1);
//     int Score = c.getInt(Column2);
//     
//     Data = Data + Name + "/" + Score + "\n";
//       }
//    while(c.moveToNext());
//   }
//   TextView tv = new TextView(this);
//   tv.setText(Data);
//   return tv;
//  }
//  catch(Exception e) {
//   Log.e("Error", "Error", e);
//  } finally {
//   if (myDB != null)
//    myDB.close();
//  }
// }
//}