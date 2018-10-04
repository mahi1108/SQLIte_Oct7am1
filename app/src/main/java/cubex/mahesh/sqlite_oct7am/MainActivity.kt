package cubex.mahesh.sqlite_oct7am

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dBase:SQLiteDatabase =
            openOrCreateDatabase("emp_db",
                    Context.MODE_PRIVATE,null)
        dBase.execSQL("create table if not exists employee(_id integer primary key autoincrement,id number,name varchar(100) ,desig varchar(100),dept varchar(100))")
        insert.setOnClickListener {
            // long insert(String table, String nullColumnHack, ContentValues values)

            var cv = ContentValues()
            cv.put("id",et1.text.toString().toInt())
            cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())
            cv.put("dept",et4.text.toString())

            var status = dBase.insert("employee",
                    null,cv)
     //       dBase.execSQL("insert into employee(125,'Kiran','HR','Admin')")
            if(status!=-1.toLong()){
                Toast.makeText(this@MainActivity,"Row Inserted",
             Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("")
            }else{
      Toast.makeText(this@MainActivity,"Row Insertion Failed..",
                        Toast.LENGTH_LONG).show()
            }


        }
        read.setOnClickListener {
            // select *from employee
/* String table, String[] columns, String selection,
String[] selectionArgs, String groupBy, String having, String orderBy */
            // select *from employee where id=123
    /*    var c:Cursor =    dBase.query("employee", null,
                    "id=?", arrayOf(et1.text.toString()),null,
                    null,null) */
            /*var c:Cursor =    dBase.query("employee", null,
                    null,null,"id",
                    "id>123",null)*/
            var c:Cursor =    dBase.query("employee", null,
                    null,null,null,
                    null,"id desc")
            var list = mutableListOf<String>()
        while(c.moveToNext()){
            list.add(c.getInt(1).toString()+"\t"+c.getString(2)+"\n"+
                                c.getString(3)+"\t"+c.getString(4))
        }
            var adapter = ArrayAdapter<String>(this@MainActivity,
                    android.R.layout.simple_list_item_single_choice,list)
            lview.adapter = adapter
        }
        update.setOnClickListener {
// update set name=value1,desig=value2 from employee where id=?
// String table, ContentValues values, String whereClause, String[] whereArgs
            var cv = ContentValues()
           cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())

       var status =  dBase.update("employee",cv,"id=?",
                   arrayOf(et1.text.toString()) )
            if(status>0){
                Toast.makeText(this@MainActivity,"Row Updated",
                        Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("")
            }else{
                Toast.makeText(this@MainActivity,"Row Updation Failed..",
                        Toast.LENGTH_LONG).show()
            }
        }
        delete.setOnClickListener {
       // String table, String whereClause, String[] whereArgs
        var status =  dBase.delete("employee","id=?",
                        arrayOf(et1.text.toString()))
            if(status>0){
                Toast.makeText(this@MainActivity,"Row Deleted",
                        Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("")
            }else{
                Toast.makeText(this@MainActivity,"Row Deletion Failed..",
                        Toast.LENGTH_LONG).show()
            }
        }

    }
}
