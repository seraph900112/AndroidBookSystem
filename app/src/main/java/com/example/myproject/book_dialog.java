import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.myproject.R;
import com.example.myproject.databinding.BookinfoDialogBinding;

public class bool_dialog extends Dialog implements View.OnClickListener {
    private Button modify;
    private Button delete;
    BookinfoDialogBinding binding;
    public bool_dialog(Activity activity) {
        super(activity);
    }
    
    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = BookinfoDialogBinding.inflate(getLayoutInflater());
        modify =binding.modify;
        delete =binding.delete;
        setContentView(binding.getRoot());
    }

    @Override
    public void onClick(View v) {

    }
}
