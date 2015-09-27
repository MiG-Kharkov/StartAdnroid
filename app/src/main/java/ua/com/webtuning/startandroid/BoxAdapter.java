package ua.com.webtuning.startandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mig on 27.09.15.
 */
public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;
    CompoundButton.OnCheckedChangeListener myCheckChangList =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    getProduct((Integer) buttonView.getTag()).box = isChecked;
                }
            };

    public BoxAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Product p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");
        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        // присваиваем чекбоксу обработчик
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        // пишем позицию
        cbBuy.setTag(position);
        // заполняем данными из товаров: в корзине или нет
        cbBuy.setChecked(p.box);
        return view;
    }

    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<>();
        for (Product p : objects) {
            if (p.box) box.add(p);
        }
        return box;
    }
}
