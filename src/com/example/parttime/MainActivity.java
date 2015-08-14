package com.example.parttime;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	// 定位四个Fragment
	private Fragment findFragment = new FindFragment();
	private Fragment meFragment = new MeFragment();
	private Fragment contractFragment = new ContractFragment();
	private Fragment weixinFragment = new WeixinFragment();

	// tab中的四个帧布局
	private FrameLayout findFrameLayout, meFrameLayout, contractFrameLayout,
			weixinFrameLayout;

	// tab中的四个帧布局中的四个图片组件
	private ImageView findImageView, meImageView, contractImageView,
			weixinImageView;

	// tab中的四个帧布局中的四个图片对应文字
	private TextView findTextView, meTextView, contractTextView,
			weixinTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化组件
		initView();

		// 初始化按钮单击事件
		initClickEvent();

		// 初始化所有fragment
		initFragment();

	}

	/**
	 * 初始化所有fragment
	 */
	private void initFragment() {
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();
		if (!weixinFragment.isAdded()) {
			fragmentTransaction.add(R.id.content, weixinFragment);
			fragmentTransaction.hide(weixinFragment);
		}
		if (!contractFragment.isAdded()) {
			fragmentTransaction.add(R.id.content, contractFragment);
			fragmentTransaction.hide(contractFragment);
		}
		if (!findFragment.isAdded()) {
			fragmentTransaction.add(R.id.content, findFragment);
			fragmentTransaction.hide(findFragment);
		}
		if (!meFragment.isAdded()) {
			fragmentTransaction.add(R.id.content, meFragment);
			fragmentTransaction.hide(meFragment);
		}
		hideAllFragment(fragmentTransaction);
		// 默认显示第一个fragment
		fragmentTransaction.show(weixinFragment);
		fragmentTransaction.commit();
	}

	/**
	 * 隐藏所有fragment
	 * 
	 * @param fragmentTransaction
	 */
	private void hideAllFragment(FragmentTransaction fragmentTransaction) {
		fragmentTransaction.hide(weixinFragment);
		fragmentTransaction.hide(contractFragment);
		fragmentTransaction.hide(findFragment);
		fragmentTransaction.hide(meFragment);
	}

	/**
	 * 初始化按钮单击事件
	 */
	private void initClickEvent() {
		findFrameLayout.setOnClickListener(this);
		meFrameLayout.setOnClickListener(this);
		contractFrameLayout.setOnClickListener(this);
		weixinFrameLayout.setOnClickListener(this);
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		findFrameLayout = (FrameLayout) findViewById(R.id.findLayout);
		meFrameLayout = (FrameLayout) findViewById(R.id.meLayout);
		contractFrameLayout = (FrameLayout) findViewById(R.id.contractLayout);
		weixinFrameLayout = (FrameLayout) findViewById(R.id.weixinLayout);

		findImageView = (ImageView) findViewById(R.id.findImageView);
		meImageView = (ImageView) findViewById(R.id.meImageView);
		contractImageView = (ImageView) findViewById(R.id.contractImageView);
		weixinImageView = (ImageView) findViewById(R.id.weixinImageView);

		findTextView = (TextView) findViewById(R.id.findTextView);
		meTextView = (TextView) findViewById(R.id.meTextView);
		contractTextView = (TextView) findViewById(R.id.contractTextView);
		weixinTextView = (TextView) findViewById(R.id.weixinTextView);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.weixinLayout:
			// 点击微信tab
			clickTab(weixinFragment);
			break;

		case R.id.contractLayout:
			// 点击联系人tab
			clickTab(contractFragment);
			break;

		case R.id.findLayout:
			// 点击发现tab
			clickTab(findFragment);
			break;

		case R.id.meLayout:
			// 点击我tab
			clickTab(meFragment);
			break;

		default:
			break;
		}
	}

	/**
	 * 点击下面的Tab按钮
	 * 
	 * @param tabFragment
	 */
	private void clickTab(Fragment tabFragment) {

		// 清除上次选中状态
		clearSeleted();

		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();

		// 隐藏所有fragment
		hideAllFragment(fragmentTransaction);

		// 显示该Fragment
		fragmentTransaction.show(tabFragment);

		// 提交事务
		fragmentTransaction.commit();

		// 改变tab的样式,设置为选中状态
		changeTabStyle(tabFragment);

	}

	/**
	 * 清除上次选中状态
	 */
	private void clearSeleted() {
		if (!weixinFragment.isHidden()) {
			weixinImageView.setImageResource(R.drawable.amk);
			weixinTextView.setTextColor(Color.parseColor("#999999"));
		}

		if (!contractFragment.isHidden()) {
			contractImageView.setImageResource(R.drawable.amg);
			contractTextView.setTextColor(Color.parseColor("#999999"));
		}

		if (!findFragment.isHidden()) {
			findImageView.setImageResource(R.drawable.amo);
			findTextView.setTextColor(Color.parseColor("#999999"));
		}

		if (!meFragment.isHidden()) {
			meImageView.setImageResource(R.drawable.ams);
			meTextView.setTextColor(Color.parseColor("#999999"));
		}
	}

	/**
	 * 根据Fragment的状态改变样式
	 */
	private void changeTabStyle(Fragment tabFragment) {
		if (tabFragment instanceof WeixinFragment) {
			weixinImageView.setImageResource(R.drawable.ami);
			weixinTextView.setTextColor(Color.parseColor("#45C01A"));
		}

		if (tabFragment instanceof ContractFragment) {
			contractImageView.setImageResource(R.drawable.ame);
			contractTextView.setTextColor(Color.parseColor("#45C01A"));
		}

		if (tabFragment instanceof FindFragment) {
			findImageView.setImageResource(R.drawable.amm);
			findTextView.setTextColor(Color.parseColor("#45C01A"));
		}

		if (tabFragment instanceof MeFragment) {
			meImageView.setImageResource(R.drawable.amq);
			meTextView.setTextColor(Color.parseColor("#45C01A"));
		}
	}
}
