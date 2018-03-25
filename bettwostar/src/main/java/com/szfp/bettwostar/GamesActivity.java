package com.szfp.bettwostar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.AppManager;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.dialog.DialogEditSureCancel;
import com.extra.widget.dialog.DialogSureCancel;
import com.extra.widget.flow.FlowAdapter;
import com.extra.widget.flow.FlowTagLayout;
import com.player.util.L;
import com.szfp.bettwostar.model.entities.GroupBean;
import com.szfp.bettwostar.model.entities.M;
import com.szfp.bettwostar.model.entities.MakeBet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.extra.utils.DataUtils.isNullString;

public class GamesActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_number)
    FlowTagLayout flNumber;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.bt_next)
    Button btNext;

    private M m;
    private String numbersStr;
    private List<String> numbers;
    private List<String> groups;
    private List<Integer> list = new ArrayList<>();
    private FlowAdapter flowAdapter;
    private List<String> rsults =new ArrayList<>();
    private String str;

    private List<GroupBean> groupBeans;
    private int gSize;
    private  GroupBean groupBean;

    private String number;
    private int numberInt;


    private StringBuffer stringBuffer;

    private List<MakeBet> makeBets;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        m = (M) getIntent().getSerializableExtra("M");
        toolbar.setTitle(m.getString1()+"   > "+m.getString2()+"   >U"+m.getUnder()) ;


    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_games;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

        if(m.getType().equals("3")){
            showInitNumber();
        }
    }

    private void initView() {
        numbersStr = getResources().getString(R.string.game_str).replace("(", "").replace(")", "");
        groups = Arrays.asList(getResources().getStringArray(R.array.aA));
        numbers = Arrays.asList(numbersStr.split(" "));
        flNumber.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        flowAdapter = new FlowAdapter(this);
        flNumber.setAdapter(flowAdapter);
        flowAdapter.onlyAddAll(numbers);
        flNumber.setOnTagSelectListener((parent, selectedList) -> {
            Collections.sort(selectedList);
            stringBuffer = new StringBuffer();
            list = selectedList;
            rsults= new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                rsults.add( numbers.get(list.get(i)) );
            }
            str = rsults.toString().replace("[","").replace("]","").replace(" ","");
            tvResult.setText(str);


        });
    }

    @OnClick(R.id.bt_next)
    public void onViewClicked() {
        if (m.getType().equals("1")){
            if (list.size() >=Integer.parseInt(m.getUnder()) ) {
                showSelectAmount();
            }else ToastUtils.showToast("The minimum number of selection is "+Integer.parseInt(m.getUnder()));
        }else if (m.getType().equals("2")){

            String [] len = m.getUnder().split(",");
            int a = 0,b = 0;b = Integer.parseInt(len[0]);
            for (int i = 0; i <len.length ; i++) {
                a = Integer.parseInt(len[i]);
                if (a>b) b=a;
            }
            if (list.size() >=b)  {
                showSelectAmount();
            }else ToastUtils.showToast("The minimum number of selection is "+b);
        } else if (m.getType().equals("3")){


            if (list.size()>=Integer.parseInt(groupBean.getNumber())){

                games = new ArrayList<>();
                String infoStr="";
                for (int i = 0; i <groupBeans.size() ; i++) {
                    infoStr = infoStr +groupBeans.get(i).getGroup() +":"+groupBeans.get(i).getNumber()+"("+groupBeans.get(i).getItem()+")\n";
                    games.addAll(Arrays.asList(groupBeans.get(i).getItem().split(",")));
                }
                L.d(infoStr);
                games.addAll(Arrays.asList(str.split(",")));
                if (checkArrayList(games)){
                    ToastUtils.showToast("Can't choose duplicate game \n"+infoStr);
                    return;

                }




                //
                groupBean.setItem(str);
                groupBeans.add(groupBean);
                numberAll = 0;
                for (int i = 0; i <groupBeans.size() ; i++) {
                    numberAll =numberAll + Integer.valueOf(groupBeans.get(i).getNumber());
                }
                if (numberAll ==Integer.valueOf(m.getUnder())){
                    ToastUtils.showToast("Please Input Group Amount ");
                    groupEdit = new DialogEditSureCancel(GamesActivity.this);
                    groupEdit.getTvTitle().setText("Please Input Amount");
                    groupEdit.getTvSure().setOnClickListener(On2ClickListener);
                    groupEdit.getTvCancel().setOnClickListener(On2ClickListener);
                    groupEdit.show();
                }else {
                    showInitNumber();
                }

            }else {
                ToastUtils.showToast("Please select more than (>="+number+") games ");
            }


        }else
        if (list.size() > 2) {
            showSelectAmount();
        } else ToastUtils.showToast("The minimum number of selection is 3");
    }

    /**
     * 输入number
     */
    private void showInitNumber() {
        if (groupBeans==null)groupBeans = new ArrayList<>();
        gSize = groupBeans.size();//==0?1:groupBeans.size();
        groupBean = new GroupBean();
        groupBean.setGroup(groups.get(gSize));
        String infoStr="";
        for (int i = 0; i <groupBeans.size() ; i++) {
            infoStr = infoStr +groupBeans.get(i).getGroup() +":"+groupBeans.get(i).getNumber()+"("+groupBeans.get(i).getItem()+")\n";
            games.addAll(Arrays.asList(groupBeans.get(i).getItem().split(",")));
        }
        if (editDialog == null) editDialog = new DialogEditSureCancel(this);
        editDialog.getTvTitle().setText("Please Input number   \n" +infoStr+groups.get(gSize)+ ":___" );
        editDialog.getTvSure().setOnClickListener(numberOnClickListener);
        editDialog.getTvCancel().setOnClickListener(numberOnClickListener);
        editDialog.show();
    }

    private DialogEditSureCancel editDialog;
    private int amount;
    private  int numberAll = 0;
    private  int numberAl = 0;
    private List<String>  games= new ArrayList<>();
    private String amountStr;
    @SuppressLint("SetTextI18n")
    private void showSelectAmount() {

        if (m.getType().equals("3")){//group



            if (groupBeans==null)groupBeans = new ArrayList<>();
            gSize = groupBeans.size();//==0?1:groupBeans.size();
            groupBean = new GroupBean();
            groupBean.setGroup(groups.get(gSize));
            groupBean.setItem(str);
            games = new ArrayList<>();

            String infoStr="";
            for (int i = 0; i <groupBeans.size() ; i++) {
                numberAll =numberAll + Integer.valueOf(groupBeans.get(i).getNumber());
                numberAl =numberAl + Integer.valueOf(groupBeans.get(i).getNumber());
                infoStr = infoStr +groupBeans.get(i).getGroup() +":"+groupBeans.get(i).getNumber()+"("+groupBeans.get(i).getItem()+")\n";

                games.addAll(Arrays.asList(groupBeans.get(i).getItem().split(",")));
            }

            games.addAll(Arrays.asList(groupBean.getItem().split(",")));
            if (checkArrayList(games)){
                ToastUtils.showToast("Can't choose duplicate game \n"+infoStr);
                return;

            }


            if (editDialog == null) editDialog = new DialogEditSureCancel(this);
            editDialog.getTvTitle().setText("Please Input number   \n" +infoStr+groups.get(gSize)+ ":___("+str+")" );
            editDialog.getTvSure().setOnClickListener(numberOnClickListener);
            editDialog.getTvCancel().setOnClickListener(numberOnClickListener);
            editDialog.show();





        }else {
            if (editDialog == null) editDialog = new DialogEditSureCancel(this);
            editDialog.getTvTitle().setText("Please Input Amount");
            editDialog.getTvSure().setOnClickListener(OnClickListener);
            editDialog.getTvCancel().setOnClickListener(OnClickListener);
            editDialog.show();
        }


    }


    private View.OnClickListener numberOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_sure:
                    number = editDialog.getEditText().getText().toString();
                    numberAll = 0;
                    numberAl = 0;
                    for (int i = 0; i <groupBeans.size() ; i++) {
                        numberAll =numberAll + Integer.valueOf(groupBeans.get(i).getNumber());
                        numberAl =numberAl + Integer.valueOf(groupBeans.get(i).getNumber());

                    }
                    if (isNullString(number)) {
                        ToastUtils.showToast("Please input number");
                        return;
                    }
                   numberAll =   numberAl + Integer.valueOf(number);
                    if (numberAll>Integer.valueOf(m.getUnder()))
                    {
                        numberAll =   numberAll - Integer.valueOf(number);
                        L.d(m.getUnder()+"  "+numberAll);
                        ToastUtils.showToast("Please input number   <="+(Integer.valueOf(m.getUnder())-numberAl));
                        return;
                    }
                    //
                    if (groupBeans.size() ==0)
                        if (Integer.parseInt(number)>=Integer.valueOf(m.getUnder())){
                            ToastUtils.showToast("Please input number   <="+(Integer.valueOf(m.getUnder())-1));
                            return;
                        }
                    groupBean.setNumber(number);
                    if (editDialog != null) editDialog.cancel();
                    flNumber.clearAllOption();
                    flowAdapter.clearAndAddAll(numbers);
                    ToastUtils.showToast("Please select more than (>="+number+") games ");

                    break;

                case R.id.tv_cancel:
                    if (editDialog != null) editDialog.cancel();
                    break;
            }
        }
    };

    /**
     * Group
     */

    private DialogSureCancel dialogSureCancel;
    private DialogEditSureCancel groupEdit;
    @SuppressLint("SetTextI18n")
    private void showNextGroup() {
        try {
            groupBeans.add(groupBean);
            if (dialogSureCancel == null){
                dialogSureCancel = new DialogSureCancel(this);
                dialogSureCancel.setContent("Whether to continue adding groups...");
                dialogSureCancel.setCancelable(false);
                dialogSureCancel.setCancelListener(v -> {
                    dialogSureCancel.cancel();
                    groupEdit = new DialogEditSureCancel(this);
                    groupEdit.getTvTitle().setText("Please Input Amount");
                    groupEdit.getTvSure().setOnClickListener(On2ClickListener);
                    groupEdit.getTvCancel().setOnClickListener(On2ClickListener);
                    groupEdit.show();

                });
                dialogSureCancel.setSureListener(v -> {
                    dialogSureCancel.cancel();
                    flNumber.clearAllOption();
                    flowAdapter.clearAndAddAll(numbers);
                });
            }

            dialogSureCancel.show();
        }catch (Exception e){
            ToastUtils.showToast("please again");
        }

    }

    private View.OnClickListener On2ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.tv_sure:
                    amountStr = groupEdit.getEditText().getText().toString();
                    if (isNullString(amountStr)) {
                        ToastUtils.showToast("Please input amount");
                        return;
                    }
                    if (Integer.valueOf(amountStr)<50){
                        ToastUtils.showToast("Your stake amount should be more than 50.");return;
                    }
                    MakeBet<List<GroupBean>> makeBet = new MakeBet<>();
                    makeBet.setType(m.getType());
                    makeBet.setSort(m.getSort());
                    makeBet.setUnder(m.getUnder());
                    makeBet.setGames(groupBeans);
                    makeBet.setAmount(amountStr);

                    if (editDialog != null) editDialog.cancel();
                    showNextGroupToR(makeBet);

                    break;

                case R.id.tv_cancel:
                    if (editDialog != null) editDialog.cancel();
                    break;
            }
        }
    };



    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.tv_sure:
                    amountStr = editDialog.getEditText().getText().toString();
                    if (isNullString(amountStr)) {
                        ToastUtils.showToast("Please input amount");
                        return;
                    }

                    if (Integer.valueOf(amountStr)<50){
                        ToastUtils.showToast("Your stake amount should be more than 50.");return;
                    }
                    MakeBet<String> makeBet = new MakeBet<>();
                    makeBet.setType(m.getType());
                    makeBet.setSort(m.getSort());
                    makeBet.setUnder(m.getUnder());
                    makeBet.setGames(str);
                    makeBet.setAmount(amountStr);

                    if (editDialog != null) editDialog.cancel();
                    showNextNapOrPermAndCombination(makeBet);

                    break;

                case R.id.tv_cancel:
                    if (editDialog != null) editDialog.cancel();
                    break;
            }
        }
    };

    private void showNextNapOrPermAndCombination(MakeBet<String> makeBet) {
        Intent intent = new Intent();
        intent.setClass(this,ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("TYPE","STRING");
        bundle.putSerializable("MAKE_BET",makeBet);
        intent.putExtras(bundle);
        startActivity(intent);
        AppManager.getAppManager().finishActivity(this);
    }
    private void showNextGroupToR(MakeBet<List<GroupBean>> makeBet) {
        Intent intent = new Intent();
        intent.setClass(this,ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("TYPE","GROUP");
        bundle.putSerializable("MAKE_BET",makeBet);
        intent.putExtras(bundle);
        startActivity(intent);
        AppManager.getAppManager().finishActivity(this);
    }


    private boolean checkArrayList(List<String> l){
        boolean f =false;

        Set<String> s = new HashSet<String>();
        for (String str : l) {
            boolean b = s.add(str);
            if(!b){
                f = true;
            }
        }
        return f;
    }
}
