package com.example.hepubliccontent.presentation.util.animationview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.hepubliccontent.R;
import com.example.hepubliccontent.presentation.util.Utilities;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Vicky Zare
 * @date 13-01-2021.
 */
public class BalloonRibbonAnimationView extends RelativeLayout {

    private static final int ANIMATION_SPEED_SLOW = 0;
    private static final int ANIMATION_SPEED_NORMAL = 1;
    private static final int ANIMATION_SPEED_FAST = 2;
    private static final int ANIMATION_TYPE_SMALL = 0;
    private static final int ANIMATION_TYPE_MEDIUM = 1;
    private static final int ANIMATION_TYPE_LARGE = 2;
    private static final int ANIMATION_DIRECTION_RANDOM = 0;
    private static final int ANIMATION_DIRECTION_TOP_AND_BOTTOM = 1;
    private static final int ANIMATION_DIRECTION_DIAGONAL = 2;
    private static final int ANIMATION_STYLE_STANDARD = 0;
    private static final int ANIMATION_STYLE_FIREWORKS = 1;

    private int speed;
    private int type;
    private int direction;
    private int style;
    private int animationDuration;

    private Handler initialHandler;
    //private Handler balloonHandler;
    private Handler ribbonHandler;
    private Handler oneShotFireworkFromStartHandler;
    private Handler oneShotFireworkFromEndHandler;
   // private Handler oneShotBigBalloonHandler;
    private Runnable initialRunnable;
    private Runnable ribbonRunnable;
    //private Runnable balloonRunnable;
    private Runnable oneShotFireworkFromEndRunnable;
    private Runnable oneShotFireworkFromStartRunnable;
    //private Runnable oneShotBigBalloonRunnable;
    private int sizeX;
    private int sizeY;
    private boolean isVisibleAlready = false;
    private int bigBalloonCount = 0;

    public BalloonRibbonAnimationView(Context context) {
        this(context,null);
    }

    public BalloonRibbonAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init(context, attrs);
    }

    public BalloonRibbonAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        try {
            if (context != null) {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BalloonRibbonAnimationView);
                speed = a.getInt(R.styleable.BalloonRibbonAnimationView_animation_speed, ANIMATION_SPEED_FAST);
                type = a.getInt(R.styleable.BalloonRibbonAnimationView_animation_type, ANIMATION_TYPE_SMALL);
                direction = a.getInt(R.styleable.BalloonRibbonAnimationView_animation_direction, ANIMATION_DIRECTION_TOP_AND_BOTTOM);
                style = a.getInt(R.styleable.BalloonRibbonAnimationView_animation_style, ANIMATION_STYLE_STANDARD);
                a.recycle();

                if (type == ANIMATION_TYPE_SMALL)
                    animationDuration = 3000;
                else if (type == ANIMATION_TYPE_MEDIUM)
                    animationDuration = 10000;
                else
                    animationDuration = 15000;

                if (style == ANIMATION_STYLE_STANDARD) {

                    initialRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (isShown() && getContext() != null && !((Activity) getContext()).isFinishing()) {
                                   // Utilities.e("initialHandler","display");
                                    try {
                                        int limit;
                                        if (type == ANIMATION_TYPE_SMALL)
                                            limit = 50;
                                        else if (type == ANIMATION_TYPE_MEDIUM)
                                            limit = 80;
                                        else
                                            limit = 100;
                                        for (int i = 0; i < limit; i++) {
                                            if (i % 2 == 0) {
                                                //emitBalloonsInitially((Activity) getContext());
                                            } else {
                                                popRibbonsInitially((Activity) getContext());
                                            }
                                        }
                                    } catch (Exception e) {
                                        Utilities.Log(e);
                                    }
                                } else {
                                    //Utilities.e("initialHandler","removeCallbacks");
                                    initialHandler.removeCallbacks(null);
                                    initialHandler.removeCallbacksAndMessages(null);
                                    initialHandler = null;
                                }
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }
                    };

                   /* balloonRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (isShown() && getContext() != null && !((Activity) getContext()).isFinishing()) {
                                    emitBalloons((Activity) getContext());
                                    startBalloonEmit();
                                } else {
                                    //Utilities.e("balloonHandler","removeCallbacks");
                                    balloonHandler.removeCallbacks(null);
                                    balloonHandler.removeCallbacksAndMessages(null);
                                    balloonHandler = null;
                                }
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }
                    };*/
                    ribbonRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (isShown() && getContext() != null && !((Activity) getContext()).isFinishing()) {
                                    popRibbons((Activity) getContext());
                                    startRibbonEmit();
                                } else {
                                    //Utilities.e("ribbonHandler","removeCallbacks");
                                    ribbonHandler.removeCallbacks(null);
                                    ribbonHandler.removeCallbacksAndMessages(null);
                                    ribbonHandler = null;
                                }
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }
                    };

                } else {

                    oneShotFireworkFromStartRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (isShown() && getContext() != null && !((Activity) getContext()).isFinishing() && getChildCount() <= 20) {
                                    OneShotAnimationFromStart((Activity) getContext());
                                    startOneShotFromStartFirework();
                                } else {
                                    oneShotFireworkFromStartHandler.removeCallbacks(null);
                                    oneShotFireworkFromStartHandler.removeCallbacksAndMessages(null);
                                    oneShotFireworkFromStartHandler = null;
                                }
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }
                    };

                    oneShotFireworkFromEndRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (isShown() && getContext() != null && !((Activity) getContext()).isFinishing() && getChildCount() <= 20) {
                                    OneShotAnimationFromEnd((Activity) getContext());
                                    startOneShotFromEndFirework();
                                } else {
                                    oneShotFireworkFromEndHandler.removeCallbacks(null);
                                    oneShotFireworkFromEndHandler.removeCallbacksAndMessages(null);
                                    oneShotFireworkFromEndHandler = null;
                                }
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }
                    };

                   /* oneShotBigBalloonRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (isShown() && getContext() != null && !((Activity) getContext()).isFinishing() && bigBalloonCount < 2) {
                                    OneShotBigBalloonAnimation((Activity) getContext(), bigBalloonCount);
                                    startOneShotBigBalloonFirework();
                                    bigBalloonCount++;
                                } else {
                                    oneShotBigBalloonHandler.removeCallbacks(null);
                                    oneShotBigBalloonHandler.removeCallbacksAndMessages(null);
                                    oneShotBigBalloonHandler = null;
                                }
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }
                    };*/
                }
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        try {
            if (visibility == VISIBLE) {
                if (!isVisibleAlready) {
                    isVisibleAlready = true;
                    if (style == ANIMATION_STYLE_STANDARD) {
                        startInitialEmit();
                        startRibbonEmit();
                        //startBalloonEmit();
                    } else {
                        startOneShotFromEndFirework();
                        //startOneShotBigBalloonFirework();
                        startOneShotFromStartFirework();
                    }
                }
            } else if (visibility == GONE) {
                isVisibleAlready = false;
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    @Override
    public void onVisibilityAggregated(boolean isVisible) {
        super.onVisibilityAggregated(isVisible);
        try {
            if (isVisible) {
                if (!isVisibleAlready) {
                    isVisibleAlready = true;
                    if (style == ANIMATION_STYLE_STANDARD) {
                        startInitialEmit();
                        startRibbonEmit();
                        //startBalloonEmit();
                    } else {
                        startOneShotFromEndFirework();
                        //startOneShotBigBalloonFirework();
                        startOneShotFromStartFirework();
                    }
                }
            } else {
                isVisibleAlready = false;
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }


    public boolean isVisibleAlready() {
        return isVisibleAlready;
    }

    public void setVisibleAlready(boolean visibleAlready) {
        isVisibleAlready = visibleAlready;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            isVisibleAlready = false;
            removeAllViews();
            Utilities.e("onDetachedFromWindow", "onDetachedFromWindow");
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    private void startOneShotFromStartFirework() {
        try {
            if (oneShotFireworkFromStartHandler != null) {
                oneShotFireworkFromStartHandler.removeCallbacks(null);
                oneShotFireworkFromStartHandler.removeCallbacksAndMessages(null);
                oneShotFireworkFromStartHandler = null;
            }
            oneShotFireworkFromStartHandler = new Handler();
            oneShotFireworkFromStartHandler.postDelayed(oneShotFireworkFromStartRunnable, 50);
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    private void startOneShotFromEndFirework() {
        try {
            if (oneShotFireworkFromEndHandler != null) {
                oneShotFireworkFromEndHandler.removeCallbacks(null);
                oneShotFireworkFromEndHandler.removeCallbacksAndMessages(null);
                oneShotFireworkFromEndHandler = null;
            }
            oneShotFireworkFromEndHandler = new Handler();
            oneShotFireworkFromEndHandler.postDelayed(oneShotFireworkFromEndRunnable, 50);
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

   /* private void startOneShotBigBalloonFirework() {
        try {
            if (oneShotBigBalloonHandler != null) {
                oneShotBigBalloonHandler.removeCallbacks(null);
                oneShotBigBalloonHandler.removeCallbacksAndMessages(null);
                oneShotBigBalloonHandler = null;
            }
            oneShotBigBalloonHandler = new Handler();
            oneShotBigBalloonHandler.postDelayed(oneShotBigBalloonRunnable, 500);
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }
*/
    private void startInitialEmit() {
        try {
            if (initialHandler != null) {
               // Utilities.e("startInitialEmit","removeCallbacks");
                initialHandler.removeCallbacks(null);
                initialHandler.removeCallbacksAndMessages(null);
                initialHandler = null;
            }
          //  Utilities.e("startInitialEmit","new Handler");
            initialHandler = new Handler();
            initialHandler.postDelayed(initialRunnable, 20);
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    /*private void startBalloonEmit() {
        try {
            if (balloonHandler != null) {
                balloonHandler.removeCallbacks(null);
                balloonHandler.removeCallbacksAndMessages(null);
                balloonHandler = null;
            }
            balloonHandler = new Handler();
            if (speed == ANIMATION_SPEED_FAST)
                balloonHandler.postDelayed(balloonRunnable, 1000);
            if (speed == ANIMATION_SPEED_NORMAL)
                balloonHandler.postDelayed(balloonRunnable, 500);
            else
                balloonHandler.postDelayed(balloonRunnable, 300);
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }*/

    private void startRibbonEmit() {
        try {
            if (ribbonHandler != null) {
                ribbonHandler.removeCallbacks(null);
                ribbonHandler.removeCallbacksAndMessages(null);
                ribbonHandler = null;
            }
            ribbonHandler = new Handler();
            if (speed == ANIMATION_SPEED_FAST)
                ribbonHandler.postDelayed(ribbonRunnable, 150);
            if (speed == ANIMATION_SPEED_NORMAL)
                ribbonHandler.postDelayed(ribbonRunnable, 100);
            else
                ribbonHandler.postDelayed(ribbonRunnable, 75);
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    public void emitBalloons(Activity activity) throws Exception {
        try {
            final ImageView imageView = new ImageView(activity);
            imageView.setImageDrawable(getBalloon(activity));
            sizeX = (int) getWidth();
            sizeY = (int) getHeight();
            final int randomX = new Random().nextInt(sizeX + (sizeX / 2)) - (sizeX / 2);
            final int randomHeight;
            int r = new Random().nextInt(4);
            if (type == ANIMATION_TYPE_SMALL)
                randomHeight = ThreadLocalRandom.current().nextInt(20, 30);
            else if (type == ANIMATION_TYPE_MEDIUM)
                randomHeight = ThreadLocalRandom.current().nextInt(30, 60);
            else
                randomHeight = ThreadLocalRandom.current().nextInt(40, 80);

            LayoutParams paramsImage = new LayoutParams(Utilities.dpToPx(randomHeight, activity.getResources()), Utilities.dpToPx(randomHeight, activity.getResources()));
            if (direction == ANIMATION_DIRECTION_RANDOM) {
                if (r == 0) {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), 0, Utilities.dpToPx(randomX, activity.getResources()) / 2, -Utilities.dpToPx(randomHeight, activity.getResources()));
                } else if (r == 1) {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()) / 2, 0, -Utilities.dpToPx(randomX, activity.getResources()), -Utilities.dpToPx(randomHeight, activity.getResources()));
                } else if (r == 2) {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()), -Utilities.dpToPx(randomHeight, activity.getResources()), 0, 0);
                } else {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()), 0, Utilities.dpToPx(randomX, activity.getResources()) / 2, -Utilities.dpToPx(randomHeight, activity.getResources()));
                }
            } else if (direction == ANIMATION_DIRECTION_DIAGONAL) {
                paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                if (r == 0 || r == 1) {
                    paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), 0, Utilities.dpToPx(randomX, activity.getResources()) / 2, -Utilities.dpToPx(randomHeight, activity.getResources()));
                } else
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()) / 2, 0, -Utilities.dpToPx(randomX, activity.getResources()), -Utilities.dpToPx(randomHeight, activity.getResources()));
            } else {
                paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()), 0, -Utilities.dpToPx(randomX, activity.getResources()) / 2, -Utilities.dpToPx(randomHeight, activity.getResources()));
            }
            imageView.setLayoutParams(paramsImage);
            addView(imageView);
            Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    try {
                        removeView(imageView);
                    } catch (Exception e) {
                        Utilities.Log(e);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    try {
                        removeView(imageView);
                    } catch (Exception e) {
                        Utilities.Log(e);
                    }
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };
            if (direction == ANIMATION_DIRECTION_RANDOM) {
                if (r == 0) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 2) {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) +(sizeY * 1.2));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                } else {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                }
            } else if (direction == ANIMATION_DIRECTION_DIAGONAL) {
                if (r == 0 || r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                }
            } else {
                ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.5));
                animationY.setDuration(animationDuration);
                animationY.addListener(animatorListener);
                animationY.start();
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    public void popRibbons(Activity activity) throws Exception {
        try {
            final ImageView imageView = new ImageView(activity);
            imageView.setImageDrawable(getRibbon(activity));
            sizeX = (int) getWidth();
            sizeY = (int) getHeight();
            final int randomHeight;
            final int randomX = new Random().nextInt(sizeX + (sizeX / 2)) - (sizeX / 2);
            int r = new Random().nextInt(4);
            if (type == ANIMATION_TYPE_SMALL) {
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_3).getConstantState())
                        || imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_4).getConstantState())) {
                    randomHeight = ThreadLocalRandom.current().nextInt(20, 30);
                } else
                    randomHeight = ThreadLocalRandom.current().nextInt(10, 15);
            } else if (type == ANIMATION_TYPE_MEDIUM) {
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_3).getConstantState())
                        || imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_4).getConstantState())) {
                    randomHeight = ThreadLocalRandom.current().nextInt(25, 50);
                } else
                    randomHeight = ThreadLocalRandom.current().nextInt(15, 25);
            } else {
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_3).getConstantState())
                        || imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_4).getConstantState())) {
                    randomHeight = ThreadLocalRandom.current().nextInt(35, 60);
                } else
                    randomHeight = ThreadLocalRandom.current().nextInt(20, 35);
            }

            LayoutParams paramsImage = new LayoutParams(Utilities.dpToPx(randomHeight, activity.getResources()), Utilities.dpToPx(randomHeight, activity.getResources()));
            if (direction == ANIMATION_DIRECTION_RANDOM) {
                if (r == 0) {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), 0, Utilities.dpToPx(randomX, activity.getResources()) / 2, -Utilities.dpToPx(randomHeight, activity.getResources()));
                } else if (r == 1) {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()) / 2, 0, -Utilities.dpToPx(randomX, activity.getResources()), -Utilities.dpToPx(randomHeight, activity.getResources()));
                } else if (r == 2) {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()), -Utilities.dpToPx(randomHeight, activity.getResources()), 0, 0);
                } else {
                    paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()), 0, Utilities.dpToPx(randomX, activity.getResources()) / 2, -Utilities.dpToPx(randomHeight, activity.getResources()));
                }
            } else if (direction == ANIMATION_DIRECTION_DIAGONAL) {
                paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                if (r == 0 || r == 1) {
                    paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), 0, Utilities.dpToPx(randomX, activity.getResources()) / 2, -Utilities.dpToPx(randomHeight, activity.getResources()));
                } else
                    paramsImage.setMargins(Utilities.dpToPx(randomX, activity.getResources()) / 2, 0, -Utilities.dpToPx(randomX, activity.getResources()), -Utilities.dpToPx(randomHeight, activity.getResources()));
            } else {
                paramsImage.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), -Utilities.dpToPx(randomHeight, activity.getResources()), Utilities.dpToPx(randomX, activity.getResources()) / 2, 0);
            }
            imageView.setLayoutParams(paramsImage);
            addView(imageView);
            Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    try {
                        removeView(imageView);
                    } catch (Exception e) {
                        Utilities.Log(e);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    try {
                        removeView(imageView);
                    } catch (Exception e) {
                        Utilities.Log(e);
                    }
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            };
            if (direction == ANIMATION_DIRECTION_RANDOM) {
                if (r == 0) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 2) {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) +(sizeY * 1.2));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                } else {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                }
            } else if (direction == ANIMATION_DIRECTION_DIAGONAL) {
                if (r == 0 || r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -(sizeY * 1.2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                }
            } else {
                ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) +(sizeY * 1.5));
                animationY.setDuration(animationDuration);
                animationY.addListener(animatorListener);
                animationY.start();
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    public void emitBalloonsInitially(Activity activity) {
        try {
            int r = new Random().nextInt(4);
            final ImageView imageView = new ImageView(activity);
            imageView.setImageDrawable(getBalloon(activity));
            sizeX = (int) getWidth();
            sizeY = (int) getHeight();
            final int randomX = new Random().nextInt(sizeX);
            final int randomHeight;
            if (type == ANIMATION_TYPE_SMALL)
                randomHeight = ThreadLocalRandom.current().nextInt(20, 30);
            else if (type == ANIMATION_TYPE_MEDIUM)
                randomHeight = ThreadLocalRandom.current().nextInt(30, 60);
            else
                randomHeight = ThreadLocalRandom.current().nextInt(40, 80);

            final int randomBottomMargin = ThreadLocalRandom.current().nextInt(0, sizeY);

            LayoutParams paramsImage =
                    new LayoutParams(Utilities.dpToPx(randomHeight,
                            activity.getResources()), Utilities.dpToPx(randomHeight,
                            activity.getResources()));
            if (r % 2 == 0) {
                paramsImage.setMargins(-Utilities.dpToPx(randomX, getResources()), 0, Utilities.dpToPx(randomX, activity.getResources()) / 2, Utilities.dpToPx(randomBottomMargin,
                        activity.getResources()));
            } else {
                paramsImage.setMargins(Utilities.dpToPx(randomX, getResources()), 0, -Utilities.dpToPx(randomX, activity.getResources()) / 2, Utilities.dpToPx(randomBottomMargin,
                        activity.getResources()));
            }
            paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            imageView.setLayoutParams(paramsImage);
            addView(imageView);

            Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    try {
                        removeView(imageView);
                    } catch (Exception e) {
                        Utilities.Log(e);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    try {
                        removeView(imageView);
                    } catch (Exception e) {
                        Utilities.Log(e);
                    }
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            };
            if (direction == ANIMATION_DIRECTION_RANDOM) {
                if (r == 0) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 2) {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) +((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                } else {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                }
            } else if (direction == ANIMATION_DIRECTION_DIAGONAL) {
                if (r == 0 || r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                }
            } else {
                ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                animationY.setDuration(animationDuration);
                animationY.addListener(animatorListener);
                animationY.start();
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }

    public void popRibbonsInitially(Activity activity) {
        try {
            int r = new Random().nextInt(4);
            final ImageView imageView = new ImageView(activity);
            imageView.setImageDrawable(getRibbon(activity));
            sizeX = (int) getWidth();
            sizeY = (int) getHeight();

            final int randomX = new Random().nextInt(sizeX);
            final int randomHeight;
            if (type == ANIMATION_TYPE_SMALL) {
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_3).getConstantState())
                        || imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_4).getConstantState())) {
                    randomHeight = ThreadLocalRandom.current().nextInt(20, 30);
                } else
                    randomHeight = ThreadLocalRandom.current().nextInt(10, 15);
            } else if (type == ANIMATION_TYPE_MEDIUM) {
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_3).getConstantState())
                        || imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_4).getConstantState())) {
                    randomHeight = ThreadLocalRandom.current().nextInt(25, 50);
                } else
                    randomHeight = ThreadLocalRandom.current().nextInt(15, 25);
            } else {
                if (imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_3).getConstantState())
                        || imageView.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.b_4).getConstantState())) {
                    randomHeight = ThreadLocalRandom.current().nextInt(35, 60);
                } else
                    randomHeight = ThreadLocalRandom.current().nextInt(20, 35);
            }

            final int randomBottomMargin = ThreadLocalRandom.current().nextInt(0, sizeY);

            LayoutParams paramsImage =
                    new LayoutParams(Utilities.dpToPx(randomHeight,
                            activity.getResources()), Utilities.dpToPx(randomHeight,
                            activity.getResources()));
            paramsImage.setMargins(Utilities.dpToPx(randomX, getResources()), Utilities.dpToPx(randomBottomMargin, activity.getResources()),
                    -Utilities.dpToPx(randomX, activity.getResources()) / 2, 0);
            paramsImage.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            imageView.setLayoutParams(paramsImage);
            addView(imageView);
            Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    removeView(imageView);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    removeView(imageView);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            };
            if (direction == ANIMATION_DIRECTION_RANDOM) {
                if (r == 0) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else if (r == 2) {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) +((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                } else {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    animationY.setDuration(animationDuration);
                    animationY.addListener(animatorListener);
                    animationY.start();
                }
            } else if (direction == ANIMATION_DIRECTION_DIAGONAL) {
                if (r == 0 || r == 1) {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", +sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                } else {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -sizeX);
                    animSetXY.setDuration(animationDuration);
                    animSetXY.playTogether(x, y);
                    animSetXY.addListener(animatorListener);
                    animSetXY.start();
                }
            } else {
                ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) +((sizeY * 1.2) + Utilities.dpToPx(randomBottomMargin, activity.getResources())));
                animationY.setDuration(animationDuration);
                animationY.addListener(animatorListener);
                animationY.start();
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }


    public void OneShotAnimationFromStart(Activity activity) throws Exception {
        final ImageView imageView = new ImageView(activity);
        imageView.setImageDrawable(getRibbon(activity));
        sizeX = (int) getWidth();
        sizeY = (int) getHeight();
        final int randomX = new Random().nextInt(sizeX / 2);
        int randomHeight = ThreadLocalRandom.current().nextInt(10, 25);
        LayoutParams paramsImage = new LayoutParams(Utilities.dpToPx(randomHeight, activity.getResources()), Utilities.dpToPx(randomHeight, activity.getResources()));
//        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//        paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), (int) (sizeY * 0.85), 0, Utilities.dpToPx(sizeY - randomX, activity.getResources()));
        paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), (int) (sizeY * 0.65), 0, Utilities.dpToPx(sizeY - randomX, activity.getResources()));

        imageView.setLayoutParams(paramsImage);
        addView(imageView);
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", +(sizeY / 2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", (float) +(sizeX));
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(3000);
                    animSetXY.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            try {
                                removeView(imageView);
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animSetXY.start();
                } catch (Exception e) {
                    Utilities.Log(e);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                try {
                    removeView(imageView);
                } catch (Exception e) {
                    Utilities.Log(e);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        ObjectAnimator imageViewRotateAnimator = ObjectAnimator.ofFloat(imageView,
                "rotation", 0f, 360f);
        imageViewRotateAnimator.setDuration(1000);
        imageViewRotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        imageViewRotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        AnimatorSet animSetXY = new AnimatorSet();
        ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 0.02) + Utilities.dpToPx((ThreadLocalRandom.current().nextInt(1, 2) * randomX), getResources())));
        ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", (float) +(sizeX * 0.75 + randomX));
        animSetXY.playTogether(x, y);
        animSetXY.setDuration(1000);
        animSetXY.addListener(animatorListener);
        animSetXY.start();
        imageViewRotateAnimator.start();

    }


    public void OneShotAnimationFromEnd(Activity activity) throws Exception {
        final ImageView imageView = new ImageView(activity);
        imageView.setImageDrawable(getRibbon(activity));
        sizeX = (int) getWidth();
        sizeY = (int) getHeight();
        final int randomX = new Random().nextInt(sizeX / 2);
        int randomHeight = ThreadLocalRandom.current().nextInt(10, 25);
        LayoutParams paramsImage = new LayoutParams(Utilities.dpToPx(randomHeight, activity.getResources()), Utilities.dpToPx(randomHeight, activity.getResources()));
//        paramsImage.setMargins(sizeX + Utilities.dpToPx(randomX, getResources()), (int) (sizeY * 0.85), 0, Utilities.dpToPx(sizeY - randomX, activity.getResources()));
        paramsImage.setMargins(sizeX + Utilities.dpToPx(randomX, getResources()), (int) (sizeY * 0.65), 0, Utilities.dpToPx(sizeY - randomX, activity.getResources()));

        imageView.setLayoutParams(paramsImage);
        addView(imageView);
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    AnimatorSet animSetXY = new AnimatorSet();
                    ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", +(sizeY / 2));
                    ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", -(sizeX));
                    animSetXY.playTogether(x, y);
                    animSetXY.setDuration(3000);
                    animSetXY.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            try {
                                removeView(imageView);
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animSetXY.start();
                } catch (Exception e) {
                    Utilities.Log(e);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                try {
                    removeView(imageView);
                } catch (Exception e) {
                    Utilities.Log(e);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        ObjectAnimator imageViewRotateAnimator = ObjectAnimator.ofFloat(imageView,
                "rotation", 0f, 360f);
        imageViewRotateAnimator.setDuration(1000);
        imageViewRotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        imageViewRotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        AnimatorSet animSetXY = new AnimatorSet();
        ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 0.02) + Utilities.dpToPx((int) ((ThreadLocalRandom.current().nextInt(1, 2) * randomX) * 0.85), getResources())));
        ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", (float) -(sizeX * 0.75 + randomX));
        animSetXY.playTogether(x, y);
        animSetXY.setDuration(1000);
        animSetXY.addListener(animatorListener);
        animSetXY.start();
        imageViewRotateAnimator.start();
    }


    public void OneShotBigBalloonAnimation(Activity activity, int itemNumber) {
        try {
            final ImageView imageView;
            imageView = new ImageView(activity);
            imageView.setImageDrawable(getBalloon(activity));

            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            final int randomX;
            if (itemNumber == 0)
                randomX = Utilities.dpToPx(90, activity.getResources());
            else if (itemNumber == 1)
                randomX = Utilities.dpToPx(125, activity.getResources());
            else
                randomX = Utilities.dpToPx(270, activity.getResources());

            LayoutParams paramsImage =
                    new LayoutParams(Utilities.dpToPx(125,
                            activity.getResources()), Utilities.dpToPx(125,
                            activity.getResources()));
            if (itemNumber == 0)
                paramsImage.setMargins(sizeX + Utilities.dpToPx(randomX, getResources()), (int) (sizeY * 0.65), 0, Utilities.dpToPx(sizeY - randomX, activity.getResources()));
            else
                paramsImage.setMargins(-Utilities.dpToPx(randomX, activity.getResources()), (int) (sizeY * 0.65), 0, Utilities.dpToPx(sizeY - randomX, activity.getResources()));

            imageView.setLayoutParams(paramsImage);
            addView(imageView);

            Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (imageView != null) {
                                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 1.3)));
                                    animationY.setDuration(3500);
                                    animationY.addListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {
                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            try {
                                                removeView(imageView);
                                            } catch (Exception e) {
                                                Utilities.Log(e);
                                            }
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    });
                                    animationY.start();
                                }
                            } catch (Exception e) {
                                Utilities.Log(e);
                            }
                        }
                    }, 350);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    try {
                        removeView(imageView);
                    } catch (Exception e) {
                        Utilities.Log(e);
                    }
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };

            if (itemNumber == 0) {
                ObjectAnimator imageViewRotateAnimator = ObjectAnimator.ofFloat(imageView,
                        "rotation", -ThreadLocalRandom.current().nextInt(0, 30), ThreadLocalRandom.current().nextInt(0, 30));
                imageViewRotateAnimator.setDuration(500);
                imageViewRotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                imageViewRotateAnimator.setRepeatMode(ValueAnimator.REVERSE);
                AnimatorSet animSetXY = new AnimatorSet();
                ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 0.05) + Utilities.dpToPx((ThreadLocalRandom.current().nextInt(1, 2) * randomX), getResources())));
                ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", (float) -(sizeX * 0.75 + randomX));
                animSetXY.playTogether(x, y);
                animSetXY.setDuration(750);
                animSetXY.addListener(animatorListener);
                animSetXY.start();
                imageViewRotateAnimator.start();
            } else {
                ObjectAnimator imageViewRotateAnimator = ObjectAnimator.ofFloat(imageView,
                        "rotation", -ThreadLocalRandom.current().nextInt(0, 30), ThreadLocalRandom.current().nextInt(0, 30));
                imageViewRotateAnimator.setDuration(500);
                imageViewRotateAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                imageViewRotateAnimator.setRepeatMode(ValueAnimator.REVERSE);
                AnimatorSet animSetXY = new AnimatorSet();
                ObjectAnimator y = ObjectAnimator.ofFloat(imageView, "translationY", (float) -((sizeY * 0.05) + Utilities.dpToPx((ThreadLocalRandom.current().nextInt(1, 2) * randomX), getResources())));
                ObjectAnimator x = ObjectAnimator.ofFloat(imageView, "translationX", (float) +(sizeX * 0.75 + randomX));
                animSetXY.playTogether(x, y);
                animSetXY.setDuration(750);
                animSetXY.addListener(animatorListener);
                animSetXY.start();
                imageViewRotateAnimator.start();
            }
        } catch (Exception e) {
            Utilities.Log(e);
        }
    }


    private static Drawable getRibbon(Activity activity) {
        TypedArray ribbons = activity.getResources().obtainTypedArray(R.array.ribbons);
        int randomRibbonId = ribbons.getResourceId(new Random().nextInt(ribbons.length()), 0);
        ribbons.recycle();
        return activity.getResources().getDrawable(randomRibbonId);
    }

    private static Drawable getBalloon(Activity activity) {
        TypedArray balloons = activity.getResources().obtainTypedArray(R.array.balloons);
        int randomBalloonId = balloons.getResourceId(new Random().nextInt(balloons.length()), 0);
        balloons.recycle();
        return activity.getResources().getDrawable(randomBalloonId);
    }

    private static Drawable getBigBalloon(Activity activity) {
        TypedArray balloons = activity.getResources().obtainTypedArray(R.array.bigBalloons);
        int randomBalloonId = balloons.getResourceId(new Random().nextInt(balloons.length()), 0);
        balloons.recycle();
        return activity.getResources().getDrawable(randomBalloonId);
    }
}
