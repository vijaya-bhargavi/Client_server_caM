import sys

import cv2

import matplotlib.pyplot as plt

S=sys.argv

img=cv2.imread(S[1],-1)

cv2.imshow("Recombined Image",img)

#plt.subplot(111),plt.imshow(img,cmap="gray"),plt.title("Recombined Image from Client")

#plt.xticks([]), plt.yticks([])

cv2.waitKey(0)

cv2.destroyAllWindows()
