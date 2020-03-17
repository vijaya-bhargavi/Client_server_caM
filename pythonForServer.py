import cv2
import matplotlib.pyplot as plt

#provide the similar to the 3 rgb paths already given in server
imgr=cv2.imread("/home/vijaya/Desktop/red.png")
imgg=cv2.imread("/home/vijaya/Desktop/green.png")
imgb=cv2.imread("/home/vijaya/Desktop/blue.png")

plt.subplot(131),plt.imshow(imgr,cmap="gray"),plt.title("Red")
plt.xticks([]), plt.yticks([])

plt.subplot(132),plt.imshow(imgg,cmap="gray"),plt.title("Green")
plt.xticks([]), plt.yticks([])

plt.subplot(133),plt.imshow(imgb,cmap="gray"),plt.title("Blue")
plt.xticks([]), plt.yticks([])

plt.show() 
