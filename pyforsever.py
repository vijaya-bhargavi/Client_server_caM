import cv2

import sys

import matplotlib.pyplot as plt

S=sys.argv

imgr=cv2.imread(S[1]+"red.png")

imgg=cv2.imread(S[1]+"green.png")

imgb=cv2.imread(S[1]+"blue.png")

 
plt.subplot(131),plt.imshow(imgr,cmap="gray"),plt.title("Red")

plt.xticks([]), plt.yticks([])

 
plt.subplot(132),plt.imshow(imgg,cmap="gray"),plt.title("Green")

plt.xticks([]), plt.yticks([])


plt.subplot(133),plt.imshow(imgb,cmap="gray"),plt.title("Blue")

plt.xticks([]), plt.yticks([])

 

plt.show()
