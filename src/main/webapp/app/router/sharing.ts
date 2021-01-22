const ShareCodeView = () => import('@/sharing/code/share-code-view.vue');

export default [
  {
    path: '/sharing/code/:uid',
    name: 'ShareCodeView',
    component: ShareCodeView,
  },
];
