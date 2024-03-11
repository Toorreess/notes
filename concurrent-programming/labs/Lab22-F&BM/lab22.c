/**
 * lab22.c
 * Created on: 2024-03-08
 * Author: JTorres
 */

#include <stdio.h>
#include <stdlib.h>

void decrypt(unsigned int *v, unsigned int *k)
{
    const unsigned int delta = 0x9e3779b9;
    unsigned int sum = 0xC6EF3720;
    int i;
    for (i = 0; i < 32; i++)
    {
        v[1] -= ((v[0] << 4) + k[2]) ^ (v[0] + sum) ^ ((v[0] >> 5) + k[3]);
        v[0] -= ((v[1] << 4) + k[0]) ^ (v[1] + sum) ^ ((v[1] >> 5) + k[1]);
        sum -= delta;
    }
}

int main(int argc, char const *argv[])
{
    // Open the source file
    FILE *fin = fopen("/home/uni/notes/concurrent-programming/labs/Lab22-F&BM/image01.png", "rb");
    if (fin == NULL)
    {
        printf("[*] ERROR: Couldn't open the file.");
        exit(-1);
    }

    // Read imgSize
    int imgSize;
    fread(&imgSize, sizeof(int), 1, fin);

    // Workout number of blocks
    int numBlocks = (imgSize % 8 == 0) ? (imgSize / 8) : (imgSize / 8 + 1);

    // Allocate memory for a buffer
    unsigned int *buffer = (unsigned int *)malloc(numBlocks * 8);
    if (buffer == NULL)
    {
        printf("[*] ERROR: Couldn't allocate the memory");
        fclose(fin);
        exit(-1);
    }

    // Read the rest of blocks in the encripted file
    int r;
    r = fread(buffer, 8, numBlocks, fin);
    if (r != numBlocks)
    {
        printf("[*] ERROR: Read %d but expected %d blocks", r, numBlocks);
        fclose(fin);
        exit(-1);
    }

    // For each block, call decrypt()
    unsigned int k[] = {128, 129, 130, 131};
    for (int i = 0; i < numBlocks; i++)
    {
        decrypt(buffer + 2 * i, k);
    }

    // Open a new file to store the image
    FILE *fout = fopen("/home/uni/notes/concurrent-programming/labs/Lab22-F&BM/result.png", "wb");
    if (fout == NULL)
    {
        printf("[*] ERROR: Couldn't open the file to write on it.");
        exit(-1);
    }

    // Write the imgSize bytes from the buffer to the file
    fwrite(buffer, imgSize, 1, fout);

    // Close files and free memory
    fclose(fin);
    fclose(fout);
    free(buffer);

    fflush(stdout);
    return EXIT_SUCCESS;
}
