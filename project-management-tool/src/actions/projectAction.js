import axios from 'axios';
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from './types';

export const createProject = (newProject, history) => async dispatch => {
  try {
    await axios.post('/api/projects', newProject);
    history.push('/dashboard');
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
export const getProjects = () => async dispatch => {
  const res = await axios.get('/api/projects');
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};

export const getProject = (projectIdentifier, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/projects/${projectIdentifier}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  } catch (error) {
    history.push('/dashboard');
  }
};

export const deleteProject = projectIdentifier => async dispatch => {
  if (window.confirm('Are you sure you want to delete the project?')) {
    await axios.delete(`/api/projects/${projectIdentifier}`);
    dispatch({ type: DELETE_PROJECT, payload: projectIdentifier });
  }
};
