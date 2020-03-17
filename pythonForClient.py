import cv2
import matplotlib.pyplot as plt

#provide the path given in the client's resultant path
img=cv2.imread("/home/vijaya/Desktop/client_result.png",-1)

cv2.imshow("Recombined Image",img)
cv2.waitKey(0)
cv2.destroyAllWindows()
