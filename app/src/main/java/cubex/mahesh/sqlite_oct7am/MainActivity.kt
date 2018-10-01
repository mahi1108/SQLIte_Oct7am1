package cubex.mahesh.sqlite_oct7am

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
/* Cursor query(String table, String[] columns, String selection,
 String[] selectionArgs, String groupBy, String having, String orderBy) */
var c : Cursor =   dBase.query("employee",null,
        null,null,null,null,
        null)
var adapter = SimpleCursorAdapter(this@MainActivity,
        R.layout.indiview,c, arrayOf("id","name","desig","dept"),
        intArrayOf(R.id.id,R.id.name,R.id.desig,R.id.dept),0)
lview.adapter = adapter

        }
        update.setOnClickListener {  }
        delete.setOnClickListener {  }

    }
}
