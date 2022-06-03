#include <core/logger.h>
#include <core/asserts.h>

// TODO: Test
#include <platform/platform.h>

int main(void) {
    KFATAL("\nA test message: %f", 3.14f);
    KERROR("\nA test message: %f", 3.14f);
    KWARN("\nA test message: %f", 3.14f);
    KINFO("\nA test message: %f", 3.14f);
    KDEBUG("\nA test message: %f", 3.14f);
    KTRACE("\nA test message: %f", 3.14f);

    platform_state state;
    if(platform_startup(&state, "GanjGame Engine TestBed", 100, 100, 640, 480)){
        while(TRUE){
            platform_pump_messages(&state);
        }
    }
    platform_shutdown(&state);

    return 0;
}