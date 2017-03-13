package com.example.cielwu.dialogtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch (v.getId()){
            //显示最简单的对话框
            case R.id.show_simple_dialog:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);//获取Builder对象
                builder.setTitle("Simple Dialog Test");//设置标题
                builder.setIcon(R.mipmap.ic_launcher);//设置图标
                builder.setMessage("this is a simple dialog just for test");//设置内容
                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on OK,which="+which,Toast.LENGTH_SHORT).show();
                    }
                });
                //设置取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on cancel,which="+which,Toast.LENGTH_SHORT).show();
                    }
                });
                //设置自己另外添加的按钮
                builder.setNeutralButton("自己添加的按钮", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on Test Button,which="+which,Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();//创建AlertDialog对象
                builder.show();//显示Dialog
                break;

            //显示列表项对话框
            case R.id.show_list_item_dialog:
                //对话框中列表项的内容
                final String itemsArray[]={"C","C++","JAVA","Android","PHP","Jsp","Python","IOS","Swift","GO","shell","R"};

                AlertDialog.Builder builder2=new AlertDialog.Builder(this);
                builder2.setTitle("list item dialog");
                builder2.setIcon(R.mipmap.ic_launcher);
                builder2.setItems(itemsArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"select "+itemsArray[which],Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on OK",Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.create();
                builder2.show();
                break;

            //显示单选项列表对话框
            case R.id.show_singlechoice_dialog:
                //对话框中单选项的内容
                final String itemsArray2[]={"C","C++","JAVA","Android","PHP","Jsp","Python","IOS","Swift","GO","shell","R"};

                AlertDialog.Builder builder3=new AlertDialog.Builder(this);
                builder3.setTitle("SingleChoice Dialog");
                builder3.setIcon(R.mipmap.ic_launcher);
                //设置单选列表项，默认选中第一个，索引为0
                builder3.setSingleChoiceItems(itemsArray2, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"select "+itemsArray2[which],Toast.LENGTH_SHORT).show();
                    }
                });
                builder3.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on OK",Toast.LENGTH_SHORT).show();
                    }
                });
                builder3.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                builder3.create();
                builder3.show();
                break;

            //显示多选项对话框
            case R.id.show_multichoice_dialog:
                //对话框中单选项的内容
                final String itemsArray3[]={"C","C++","JAVA","Android"};
                //设置多选列表项时的第二个参数，下列表示默认选择第二项
                final boolean selection[]={false,true,false,false};

                AlertDialog.Builder builder4=new AlertDialog.Builder(this);
                builder4.setTitle("MultiChoice Dialog");
                builder4.setIcon(R.mipmap.ic_launcher);
                //设置多选列表项
                builder4.setMultiChoiceItems(itemsArray3, selection, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(MainActivity.this,"C:"+selection[0]+" C++:"+selection[1]+" JAVA:"+selection[2]+" Android:"+selection[3],
                                Toast.LENGTH_SHORT).show();
                    }
                });

                builder4.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on OK",Toast.LENGTH_SHORT).show();
                    }
                });
                builder4.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                builder4.create();
                builder4.show();
                break;

            //显示自定义Adapter的对话框
            case R.id.show_adapter_dialog:
                AlertDialog.Builder builder5=new AlertDialog.Builder(this);
                builder5.setTitle("Adapter Dialog");
                builder5.setIcon(R.mipmap.ic_launcher);

                //设置Adapter
                final ArrayList<HashMap<String,Object>> list=getData();
                builder5.setAdapter(new MyDialogAdapter(list, MainActivity.this), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"select "+list.get(which).get("name"),Toast.LENGTH_SHORT).show();
                    }
                });

                builder5.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on OK",Toast.LENGTH_SHORT).show();
                    }
                });
                builder5.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                builder5.create();
                builder5.show();
                break;

            //显示含有自定义view的对话框
            case R.id.show_view_dialog:
                //初始化View
//                LayoutInflater layoutInflater=getLayoutInflater();
//                RelativeLayout myView= (RelativeLayout) layoutInflater.inflate(R.layout.test_view,null);
                //或者
                RelativeLayout myView= (RelativeLayout) getLayoutInflater().inflate(R.layout.test_view,null);

                AlertDialog.Builder builder6=new AlertDialog.Builder(this);
                builder6.setTitle("View Dialog");
                builder6.setIcon(R.mipmap.ic_launcher);
                builder6.setView(myView);//设置自定义的View
                builder6.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on OK",Toast.LENGTH_SHORT).show();
                    }
                });
                builder6.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"click on cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                builder6.create();
                builder6.show();
                break;

            default:
                break;
        }
    }

    private ArrayList<HashMap<String,Object>> getData(){
        ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<10;i++){
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("image",R.mipmap.ic_launcher);
            map.put("name","张三"+i);
            map.put("address","asd"+i);
            list.add(map);
        }
        return list;
    }

    class MyDialogAdapter extends BaseAdapter{

        private static final String TAG ="MyDialogAdapter";
        private ArrayList<HashMap<String,Object>> list;
        private Context context;

        public MyDialogAdapter(ArrayList<HashMap<String,Object>> list, Context context) {
            this.list=list;
            this.context=context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            if(convertView==null){
                LayoutInflater inflater= LayoutInflater.from(context);
                convertView=inflater.inflate(R.layout.dialog_list_item,null);
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            HashMap<String,Object> map=list.get(position);
                viewHolder.image.setImageResource((Integer) map.get("image"));
                viewHolder.name.setText(map.get("name")+"");
                viewHolder.address.setText(map.get("address")+"");

            Log.i(TAG,map.get("image")+"");
            Log.i(TAG,map.get("name")+"");
            Log.i(TAG,map.get("address")+"");

            return convertView;
        }

        class ViewHolder{
            private ImageView image;
            private TextView name;
            private TextView address;
            ViewHolder(View view){
                image= (ImageView) view.findViewById(R.id.imageView);
                name= (TextView) view.findViewById(R.id.name);
                address= (TextView) view.findViewById(R.id.address);
            }
        }
    }
}
