#include <stdio.h>

int main(int argc, char** argv)
{
    printf("CustomLauncher started\n\n");

    printf("arguments:\n");
    for (int i=0; i<argc; i++)
    {
        printf(argv[i]);
        printf("\n");
    }
    return 0;
}