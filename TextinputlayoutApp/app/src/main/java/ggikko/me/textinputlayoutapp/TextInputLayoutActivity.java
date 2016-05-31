package ggikko.me.textinputlayoutapp;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextInputLayoutActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @BindView(R.id.user_id_wrapper) TextInputLayout user_id_wrapper;
    @BindView(R.id.user_password_wrapper) TextInputLayout user_password_wrapper;

    @BindColor(R.color.red) int red;


    @OnClick(R.id.login)
    void callLogin(){

        boolean userIdOk = false;
        boolean userPasswordOk = false;

        hideKeyboard();

        String username = user_id_wrapper.getEditText().getText().toString();
        String password = user_password_wrapper.getEditText().getText().toString();

        if (!validateEmail(username)) {
//            user_id_wrapper.getEditText().setHighlightColor(red);
//            user_id_wrapper.getEditText().setHintTextColor(red);
            user_id_wrapper.setError("이메일을 다시 확인해주세요!");
//            user_id_wrapper.setHintTextAppearance(R.style.TextErrorAppearance);
        }else{
//            user_id_wrapper.setHintTextAppearance(R.style.TextNormalAppearance);
            user_id_wrapper.setError("");
            userIdOk = true;
        }

        if (!validatePassword(password)) {
            user_password_wrapper.setHintTextAppearance(R.style.TextErrorAppearance2);
            user_password_wrapper.setError("패스워드를 다시 확인해주세요!");
        }else{
            user_password_wrapper.setError("");
//            user_password_wrapper.setHintTextAppearance(R.style.TextNormalAppearance);
            userPasswordOk = true;
        }

        if (userIdOk && userPasswordOk) {
            doLogin();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * hide keyboard
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * email validation
     * @param email
     * @return
     */
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * password validation 2자 이상
     * @param password
     * @return
     */
    public boolean validatePassword(String password) {
        return password.length() > 2;
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "로그인에 성공하셨습니다!", Toast.LENGTH_SHORT).show();
    }
}
