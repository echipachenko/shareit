const ShareCodeView = () => import('@/sharing/code/share-code-view.vue');
const ShareImageView = () => import('@/sharing/image/share-image-view.vue');

export default [
  {
    path: '/sharing/code/:uid',
    name: 'ShareCodeView',
    component: ShareCodeView,
  },
  {
    path: '/sharing/image/:uid',
    name: 'ShareImageView',
    component: ShareImageView,
  },
];
