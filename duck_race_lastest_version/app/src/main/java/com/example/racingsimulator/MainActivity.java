package com.example.racingsimulator;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SeekBar s1, s2, s3;
    private ImageButton btnReset, btnStart1, btnCancel, btnStart;
    private Button btnReturn, btnContinue;
    private ImageView number1, number2, number3, flag;
    private CheckBox check1, check2, check3;
    private EditText money1, money2, money3;
    private RelativeLayout board;
    private LinearLayout watch_frame, racingFrame, buttonFrame, buttonFrame1, countFrame;
    private TextView balance, txtTimer, result;
    private int second = 0;
    private  boolean running;
    int currentButton = 1;
    Handler handler = new Handler();
    int point = 100;
    int bet1, bet2, bet3, total, pre_point;
    MediaPlayer mp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag = findViewById(R.id.flag);
        countFrame = findViewById(R.id.countFrame);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        btnStart1 = findViewById(R.id.btnStart1);
        btnCancel = findViewById(R.id.btnCancel);
        buttonFrame = findViewById(R.id.buttonFrame);
        buttonFrame1 = findViewById(R.id.buttonFrame1);
        watch_frame = findViewById(R.id.watch_frame);
        racingFrame = findViewById(R.id.racingFrame);
        board = findViewById(R.id.board);
        result = findViewById(R.id.result);
        balance = findViewById(R.id.balance);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnContinue = findViewById(R.id.btnContinue);
        btnReturn = findViewById(R.id.btnReturn);
        check1 = findViewById(R.id.checkbox1);
        check2 = findViewById(R.id.checkbox2);
        check3 = findViewById(R.id.checkbox3);
        money1 = findViewById(R.id.money1);
        money2 = findViewById(R.id.money2);
        money3 = findViewById(R.id.money3);
        s1 = findViewById(R.id.seek1);
        s2 = findViewById(R.id.seek2);
        s3 = findViewById(R.id.seek3);
        txtTimer = findViewById(R.id.TextTimer);

        //mặc định ban đầu bảng kết quả và layout chứa phím cancel sẽ ẩn
        buttonFrame1.setVisibility((View.INVISIBLE));
        board.setVisibility(View.INVISIBLE);
        countFrame.setVisibility(View.INVISIBLE);
        flag.setVisibility(View.INVISIBLE);

        //ban đầu khi chưa tick chọn sẽ set giá trị ô nhập tiền là false
        money1.setEnabled(false);
        money2.setEnabled(false);
        money3.setEnabled(false);

        //hiển thị số tiền mặc định ban đầu
        balance.setText("BALANCE: " + point);

        //hàm chạy bộ đếm thời gian
        runTimer();

        //phát nhạc ở menu
        playMenu();

        //XỬ LÝ SỰ KIỆN KHI TICK BOX
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    money1.setEnabled(true);
                } else {
                    money1.setEnabled(false);
                    money1.setText(null);
                }
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    money2.setEnabled(true);
                } else {
                    money2.setEnabled(false);
                    money2.setText(null);
                }
            }
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    money3.setEnabled(true);
                } else {
                    money3.setEnabled(false);
                    money3.setText(null);
                }
            }
        });

        // NGĂN KÉO THUMB TRÊN THANH SEEKBAR
        s1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        s2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        s3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //tốc độ di chuyển
        CountDownTimer countDownTimer = new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long l) {
                int n = 3;
                Random random = new Random();
                int r1 = random.nextInt(n);
                int r2 = random.nextInt(n);
                int r3 = random.nextInt(n);

                s1.setProgress(s1.getProgress() + r1);
                s2.setProgress(s2.getProgress() + r2);
                s3.setProgress(s3.getProgress() + r3);

                String timer = txtTimer.getText().toString(); // trích thời gian từ bộ đếm thành văn bản

                //code xử lý số dư sau khi đua xong
                pre_point = point;
                if(s1.getProgress() >= s1.getMax() - 4) {
                    this.onFinish();
                    this.cancel();
                    s1.setVisibility(View.INVISIBLE);
                    money1.setBackgroundResource(R.drawable.finish);
                    running = false; //stop timer
                    if (!money1.getText().toString().isEmpty()) {
                        bet1 = Integer.parseInt(money1.getText().toString());
                        point += bet1;
                    }
                    if (!money2.getText().toString().isEmpty()) {
                        bet2 = Integer.parseInt(money2.getText().toString());
                        point -= bet2;
                    }
                    if (!money3.getText().toString().isEmpty()) {
                        bet3 = Integer.parseInt(money3.getText().toString());
                        point -= bet3;
                    }
                    balance.setText("BALANCE: " + point);
                    result.setText("DUCK 1 WON!\n" + "TIME: "+ timer +"\nBALANCE: " + point);
                    musicResult();
                }
                if(s2.getProgress() >= s2.getMax() - 4) {
                    this.onFinish();
                    this.cancel();
                    s2.setVisibility(View.INVISIBLE);
                    money2.setBackgroundResource(R.drawable.finish);
                    running = false; //stop timer
                    if (money1.getVisibility() == View.VISIBLE && !money1.getText().toString().isEmpty()) {
                        bet1 = Integer.parseInt(money1.getText().toString());
                        point -= bet1;
                    }
                    if (money2.getVisibility() == View.VISIBLE && !money2.getText().toString().isEmpty()) {
                        bet2 = Integer.parseInt(money2.getText().toString());
                        point += bet2;
                    }
                    if (money3.getVisibility() == View.VISIBLE && !money3.getText().toString().isEmpty()) {
                        bet3 = Integer.parseInt(money3.getText().toString());
                        point -= bet3;
                    }
                    balance.setText("BALANCE: " + point);
                    result.setText("DUCK 2 WON!\n" + "TIME: "+ timer +"\nBALANCE: " + point);
                    musicResult();
                }
                if(s3.getProgress() >= s3.getMax() - 4) {
                    this.onFinish();
                    this.cancel();
                    s3.setVisibility(View.INVISIBLE);
                    money3.setBackgroundResource(R.drawable.finish);
                    running = false; //stop timer
                    if (money1.getVisibility() == View.VISIBLE && !money1.getText().toString().isEmpty()) {
                        bet1 = Integer.parseInt(money1.getText().toString());
                        point -= bet1;
                    }
                    if (money2.getVisibility() == View.VISIBLE && !money2.getText().toString().isEmpty()) {
                        bet2 = Integer.parseInt(money2.getText().toString());
                        point -= bet2;
                    }
                    if (money3.getVisibility() == View.VISIBLE && !money3.getText().toString().isEmpty()) {
                        bet3 = Integer.parseInt(money3.getText().toString());
                        point += bet3;
                    }
                    balance.setText("BALANCE: " + point);
                    result.setText("DUCK 3 WON!\n" + "TIME: "+ timer +"\nBALANCE: " + point);
                    musicResult();
                }
            }

            @Override
            public void onFinish() {
                btnCancel.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        board.setVisibility(View.VISIBLE); //hiện bảng kết quả khi chạy xong

                        //ẩn mấy thứ lằng nhằng khi bảng kq hiện lên, nhìn cho đỡ rối mắt
                        btnCancel.setEnabled(true);
                        racingFrame.setVisibility(View.INVISIBLE);
                        buttonFrame.setVisibility(View.INVISIBLE);
                        watch_frame.setVisibility(View.INVISIBLE);
                        buttonFrame1.setVisibility(View.INVISIBLE);
                    }
                }, 2000);
            }
        };

        //nút bắt đầu
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0; //tổng cược ban đầu là 0

                if (point == 0)
                    Toast.makeText(MainActivity.this, "YOU OUT OF MONEY! PLEASE RESET!", Toast.LENGTH_SHORT).show();
                else {
                    //kiểm tra số tiền cược có vượt quá balance hay ko
                    if (!money1.getText().toString().isEmpty())
                        total += Integer.parseInt(money1.getText().toString());
                    if (!money2.getText().toString().isEmpty())
                        total += Integer.parseInt(money2.getText().toString());
                    if (!money3.getText().toString().isEmpty())
                        total += Integer.parseInt(money3.getText().toString());
                    if(!check1.isChecked() && !check2.isChecked() && !check3.isChecked())
                        Toast.makeText(MainActivity.this, "Requires at least 1 selected box!", Toast.LENGTH_SHORT).show();
                    else if(total == 0)
                        Toast.makeText(MainActivity.this, "Please set money to bet!", Toast.LENGTH_SHORT).show();
                    else if (total <= point) {
                            playStart(); //nhạc lúc bắt đầu

                            //khi start sẽ không cho tương tác
                            btnStart.setEnabled(false);
                            check1.setEnabled(false);
                            check2.setEnabled(false);
                            check3.setEnabled(false);
                            money1.setEnabled(false);
                            money2.setEnabled(false);
                            money3.setEnabled(false);
                            buttonFrame.setVisibility(View.INVISIBLE);
                            buttonFrame1.setVisibility(View.VISIBLE);
                            btnCancel.setEnabled(false);
                            btnStart1.setEnabled(false);

                            //đếm ngược
                            startCount();

                            //delay 3s rồi mới bắt đầu chạy
                            handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                countDownTimer.start();
                                playRace();
                                running = true; //run timer
                                second = 1; //để timer bắt kịp với thời gian bị delay lần đầu tiên nhấn nút
                                btnCancel.setEnabled(true);
                            }
                        }, 4000);
                        } else
                            Toast.makeText(MainActivity.this, "Total bet must not exceed balance", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //nút đặt lại tất cả
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMenu();

                // đặt lại balance
                point = 100;
                balance.setText("BALANCE: " + point);

                //progress ban đầu là 6 (để hình ảnh ko bị mất góc)
                s1.setProgress(6);
                s2.setProgress(6);
                s3.setProgress(6);

                countDownTimer.cancel(); //dừng chạy
                running = false; //stop timer
                second = 0; //reset timer

                //hiện lại seekbar
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);

                //cho phép tương tác lại với các đối tượng
                btnStart.setEnabled(true);
                check1.setEnabled(true);
                check2.setEnabled(true);
                check3.setEnabled(true);

                //bỏ tick tất cả ô
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);

                //đặt lại ảnh tổ vịt ban đầu
                money1.setBackgroundResource(R.drawable.nest);
                money2.setBackgroundResource(R.drawable.nest);
                money3.setBackgroundResource(R.drawable.nest);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMenu();

                board.setVisibility(View.INVISIBLE);
                buttonFrame1.setVisibility(View.INVISIBLE);
                buttonFrame.setVisibility(View.VISIBLE);

                //progress ban đầu là 6 (để hình ảnh ko bị mất góc)
                s1.setProgress(6);
                s2.setProgress(6);
                s3.setProgress(6);

                second = 0; //reset timer

                //cho phép tương tác lại với các đối tượng
                btnStart.setEnabled(true);
                check1.setEnabled(true);
                check2.setEnabled(true);
                check3.setEnabled(true);

                //hiện lại seekbar
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);

                //đặt lại ảnh tổ vịt ban đầu
                money1.setBackgroundResource(R.drawable.nest);
                money2.setBackgroundResource(R.drawable.nest);
                money3.setBackgroundResource(R.drawable.nest);

                //kiểm tra xem ô chọn có được tick ko -> cho tương tác với ô đặt tiền
                if(check1.isChecked()) money1.setEnabled(true);
                if(check2.isChecked()) money2.setEnabled(true);
                if(check3.isChecked()) money3.setEnabled(true);

                //hiện lại UI
                racingFrame.setVisibility(View.VISIBLE);
                buttonFrame.setVisibility(View.VISIBLE);
                watch_frame.setVisibility(View.VISIBLE);
            }
        });

        //nút cancel giúp hủy bỏ chặng đua hiện tại
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMenu();

                //progress ban đầu là 6 (để hình ảnh ko bị mất góc)
                s1.setProgress(6);
                s2.setProgress(6);
                s3.setProgress(6);

                countDownTimer.cancel(); //dừng chạy
                running = false; //stop timer
                second = 0; //reset timer

                //hiện lại seekbar
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);

                //cho phép tương tác lại với các đối tượng
                btnStart.setEnabled(true);
                check1.setEnabled(true);
                check2.setEnabled(true);
                check3.setEnabled(true);

                //kiểm tra xem ô chọn có được tick ko -> cho tương tác với ô đặt tiền
                if(check1.isChecked()) money1.setEnabled(true);
                if(check2.isChecked()) money2.setEnabled(true);
                if(check3.isChecked()) money3.setEnabled(true);

                //đặt lại ảnh tổ vịt ban đầu
                money1.setBackgroundResource(R.drawable.nest);
                money2.setBackgroundResource(R.drawable.nest);
                money3.setBackgroundResource(R.drawable.nest);

                //hiện lại layout chứa nút reset, ẩn layout cancel
                buttonFrame1.setVisibility(View.INVISIBLE);
                buttonFrame.setVisibility(View.VISIBLE);
            }
        });

        //nút replay chơi tiếp
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFrame1.setVisibility(View.VISIBLE);
                buttonFrame.setVisibility(View.INVISIBLE);
                btnCancel.setEnabled(false);
                if(total <= point) {
                    playStart(); //nhạc lúc bắt đầu

                    //đếm ngược
                    startCount();

                    //delay 3s rồi mới bắt đầu chạy
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            countDownTimer.start();
                            playRace();
                            btnCancel.setEnabled(true);
                            running = true; //run timer
                            second = 1; //để timer bắt kịp với thời gian bị delay lần đầu tiên nhấn nút
                        }
                    }, 4000);
                    //progress ban đầu là 6 (để hình ảnh ko bị mất góc)
                    s1.setProgress(6);
                    s2.setProgress(6);
                    s3.setProgress(6);

                    second = 0; //reset timer

                    //hiện lại seekbar
                    s1.setVisibility(View.VISIBLE);
                    s2.setVisibility(View.VISIBLE);
                    s3.setVisibility(View.VISIBLE);

                    //đặt lại ảnh tổ vịt ban đầu
                    money1.setBackgroundResource(R.drawable.nest);
                    money2.setBackgroundResource(R.drawable.nest);
                    money3.setBackgroundResource(R.drawable.nest);

                    //hiện lại UI
                    racingFrame.setVisibility(View.VISIBLE);
                    buttonFrame.setVisibility(View.VISIBLE);
                    watch_frame.setVisibility(View.VISIBLE);

                    board.setVisibility(View.INVISIBLE); // ẩn bảng kết quả
                } else Toast.makeText(MainActivity.this, "Total bet must not exceed balance!\nPlease click continue button to bet again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //hàm bộ đếm thời gian
    private void runTimer() {
        final TextView txtTimer = (TextView) findViewById(R.id.TextTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int secs = second % 60;
                int mins = (second % 3600) /60;

                if(btnCancel.isEnabled() == false) handler.removeCallbacks(this);
                else {
                    String time = String.format(Locale.getDefault(), "%02d:%02d", mins, secs);
                    txtTimer.setText(time);

                    if (running) {
                        second++;
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    //nhạc nền menu
    public void playMenu(){
        if(mp != null){
            mp.release();
        }
        mp = MediaPlayer.create(MainActivity.this, R.raw.menu);
        mp.start();
    }

    public void playRace(){
        if(mp != null){
            mp.release();
        }
        mp = MediaPlayer.create(MainActivity.this, R.raw.race);
        mp.start();
    }

    public void playStart(){
        if(mp != null){
            mp.release();
        }
        mp = MediaPlayer.create(MainActivity.this, R.raw.start);
        mp.start();
    }

    public void playWin(){
        if(mp != null){
            mp.release();
        }
        mp = MediaPlayer.create(MainActivity.this, R.raw.win);
        mp.start();
    }
    public void playLose(){
        if(mp != null){
            mp.release();
        }
        mp = MediaPlayer.create(MainActivity.this, R.raw.lose);
        mp.start();
    }
    public void musicResult() {
        //nhạc thắng nếu ko mất điểm sau khi đua, ngược lại sẽ phát nhạc thua
        if(point < pre_point)
            playLose();
        else
            playWin();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.release();
    }

    private void startCount() {
        number1.setVisibility(View.INVISIBLE);
        number2.setVisibility(View.INVISIBLE);
        number3.setVisibility(View.INVISIBLE);
        countFrame.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (currentButton) {
                    case 1:
                        number3.setVisibility(View.VISIBLE);
                        currentButton++;
                        break;
                    case 2:
                        number2.setVisibility(View.VISIBLE);
                        currentButton++;
                        break;
                    case 3:
                        number1.setVisibility(View.VISIBLE);
                        currentButton++;
                        break;
                    case 4:
                        countFrame.setVisibility(View.INVISIBLE);
                        flag.setVisibility(View.VISIBLE);
                        currentButton++;
                        break;
                    case 5:
                        flag.setVisibility(View.INVISIBLE);
                        currentButton++;
                        break;
                }
                handler.postDelayed(this, 1000);
                if(currentButton == 6) {
                    currentButton = 1;
                    handler.removeCallbacks(this);
                }
            }
        }, 1000);
    }
}