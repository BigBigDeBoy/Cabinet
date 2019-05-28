package com.ticket.arms.integration;

import java.lang.reflect.Method;

import static com.ticket.arms.base.Platform.DEPENDENCY_ANDROID_EVENTBUS;
import static com.ticket.arms.base.Platform.DEPENDENCY_EVENTBUS;

/**
 * @author :dadade
 * date   :2019-05-27 12:00
 * desc   :
 *  EventBus 的管理类, Arms 核心库并不会依赖某个 EventBus, 如果您想使用 EventBus, 则请在项目中自行依赖对应的 EventBus, 如果不想使用则不依赖
 *  支持 greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
 *  这个类并不能完全做到 EventBus 对外界的零耦合, 只能降低耦合, 因为两个 EventBus 的部分功能使用方法差别太大, 做到完全解耦代价太大
 *  允许同时使用两个 EventBus 但不建议这样做, 建议使用 AndroidEventBus, 特别是组件化项目, 原因请看 https://github.com/hehonghui/AndroidEventBus/issues/49
 */
public class EventBusManager {
    private static volatile EventBusManager sInstance;

    public static EventBusManager getInstance() {
        if (sInstance == null) {
            synchronized (EventBusManager.class) {
                if (sInstance == null) {
                    sInstance = new EventBusManager();
                }
            }
        }
        return sInstance;
    }

    public EventBusManager() {
    }

    /**
     * 注册订阅者
     *
     * @param subscriber
     */
    public void register(Object subscriber) {
        if (DEPENDENCY_ANDROID_EVENTBUS) {
            org.simple.eventbus.EventBus.getDefault().register(subscriber);
        }
        if (DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 注销订阅者
     *
     * @param subscriber
     */
    public void unregister(Object subscriber) {
        if (DEPENDENCY_ANDROID_EVENTBUS) {
            org.simple.eventbus.EventBus.getDefault().unregister(subscriber);
        }
        if (DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.getDefault().unregister(subscriber);
        }
    }

    /**
     * 发送事件
     *
     * @param event
     */
    public void post(Object event) {
        if (DEPENDENCY_ANDROID_EVENTBUS) {
            org.simple.eventbus.EventBus.getDefault().post(event);
        } else if (DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.getDefault().post(event);
        }
    }

    /**
     * 粘性事件
     *
     * @param event
     */
    public void postSticky(Object event) {
        if (DEPENDENCY_ANDROID_EVENTBUS) {
            org.simple.eventbus.EventBus.getDefault().postSticky(event);
        } else if (DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.getDefault().postSticky(event);
        }
    }

    /**
     * 注销黏性事件
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> T removeStickyEvent(Class<T> eventType) {
        if (DEPENDENCY_ANDROID_EVENTBUS) {
            org.simple.eventbus.EventBus.getDefault().removeStickyEvent(eventType);
            return null;
        } else if (DEPENDENCY_EVENTBUS) {
            return org.greenrobot.eventbus.EventBus.getDefault().removeStickyEvent(eventType);
        }
        return null;
    }

    /**
     * 清除订阅者和事件的缓存
     */
    public void clear() {
        if (DEPENDENCY_ANDROID_EVENTBUS) {
            org.simple.eventbus.EventBus.getDefault().clear();
        } else if (DEPENDENCY_EVENTBUS) {
            org.greenrobot.eventbus.EventBus.clearCaches();
        }
    }

    private boolean haveAnnotation(Object subscriber) {
        boolean skipSuperClasses = false;
        Class<?> clazz = subscriber.getClass();
        while (clazz != null && !isSystemClass(clazz.getName()) && !skipSuperClasses) {
            Method[] allMethods;
            try {
                allMethods = clazz.getDeclaredMethods();
            } catch (Throwable th) {
                try {
                    allMethods = clazz.getMethods();
                }catch (Throwable th2){
                    continue;
                }finally {
                    skipSuperClasses=true;
                }
            }
            for (int i = 0; i < allMethods.length; i++) {
                Method method = allMethods[i];
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (method.isAnnotationPresent(org.greenrobot.eventbus.Subscribe.class)&&parameterTypes.length==1){
                    return true;
                }
            }
            clazz=clazz.getSuperclass();
        }

        return false;

    }

    private boolean isSystemClass(String name) {
        return name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.");
    }


}
